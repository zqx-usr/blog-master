package top.yehonghan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import top.yehonghan.constant.CommonConst;
import top.yehonghan.dto.EmailDTO;
import top.yehonghan.dto.UserAreaDTO;
import top.yehonghan.dto.UserBackDTO;
import top.yehonghan.dto.UserInfoDTO;
import top.yehonghan.entity.UserAuth;
import top.yehonghan.entity.UserInfo;
import top.yehonghan.entity.UserRole;
import top.yehonghan.enums.LoginMethodEnum;
import top.yehonghan.enums.RoleEnum;
import top.yehonghan.exception.BizException;
import top.yehonghan.mapper.UserInfoMapper;
import top.yehonghan.mapper.UserRoleMapper;
import top.yehonghan.service.BlogInfoService;
import top.yehonghan.service.RedisService;
import top.yehonghan.service.UserAuthService;
import top.yehonghan.mapper.UserAuthMapper;
import org.springframework.stereotype.Service;
import top.yehonghan.strategy.context.SocialLoginStrategyContext;
import top.yehonghan.util.PageUtils;
import top.yehonghan.util.UserUtils;
import top.yehonghan.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static top.yehonghan.constant.CommonConst.*;
import static top.yehonghan.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static top.yehonghan.constant.RedisPrefixConst.*;
import static top.yehonghan.enums.UserAreaTypeEnum.getUserAreaType;
import static top.yehonghan.util.CommonUtils.checkEmail;
import static top.yehonghan.util.CommonUtils.getRandomCode;

/**
* @author 86152
* @description ????????????tb_user_auth?????????????????????Service??????
* @createDate 2022-12-11 14:40:22
*/
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService{
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogInfoService blogInfoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SocialLoginStrategyContext socialLoginStrategyContext;

    @Override
    public List<UserAreaDTO> listUserAreas(ConditionVO conditionVO) {
        List<UserAreaDTO> userAreaDTOList = new ArrayList<>();
        switch (Objects.requireNonNull(getUserAreaType(conditionVO.getType()))) {
            case USER:
                // ??????????????????????????????
                Object userArea = redisService.get(USER_AREA);
                if (Objects.nonNull(userArea)) {
                    userAreaDTOList = JSON.parseObject(userArea.toString(), List.class);
                }
                return userAreaDTOList;
            case VISITOR:
                // ????????????????????????
                Map<String, Object> visitorArea = redisService.hGetAll(VISITOR_AREA);
                if (Objects.nonNull(visitorArea)) {
                    userAreaDTOList = visitorArea.entrySet().stream()
                            .map(item -> UserAreaDTO.builder()
                                    .name(item.getKey())
                                    .value(Long.valueOf(item.getValue().toString()))
                                    .build())
                            .collect(Collectors.toList());
                }
                return userAreaDTOList;
            default:
                break;
        }
        return userAreaDTOList;
    }

    @Override
    public void sendCode(String username) {
        // ????????????????????????
        if (!checkEmail(username)) {
            throw new BizException("?????????????????????");
        }
        // ?????????????????????????????????
        String code = getRandomCode();
        // ???????????????
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("?????????")
                .content("?????????????????? " + code + " ?????????15????????????????????????????????????")
                .build();
        // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // ??????????????????redis????????????????????????15??????
        redisService.set(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserVO user) {
        // ????????????????????????
        if (checkUser(user)) {
            throw new BizException("?????????????????????");
        }
        // ??????????????????
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(CommonConst.DEFAULT_NICKNAME + IdWorker.getId())
                .avatar(blogInfoService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);
        // ??????????????????
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        // ??????????????????
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .loginType(LoginMethodEnum.EMAIL.getType())
                .build();
        userAuthMapper.insert(userAuth);
    }

    @Override
    public void updatePassword(UserVO user) {
        // ????????????????????????
        if (!checkUser(user)) {
            throw new BizException("?????????????????????");
        }
        // ???????????????????????????
        userAuthMapper.update(new UserAuth(), new LambdaUpdateWrapper<UserAuth>()
                .set(UserAuth::getPassword, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .eq(UserAuth::getUsername, user.getUsername()));
    }

    @Override
    public void updateAdminPassword(PasswordVO passwordVO) {
        // ???????????????????????????
        UserAuth user = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, UserUtils.getLoginUser().getId()));
        // ????????????????????????????????????????????????
        if (Objects.nonNull(user) && BCrypt.checkpw(passwordVO.getOldPassword(), user.getPassword())) {
            UserAuth userAuth = UserAuth.builder()
                    .id(UserUtils.getLoginUser().getId())
                    .password(BCrypt.hashpw(passwordVO.getNewPassword(), BCrypt.gensalt()))
                    .build();
            userAuthMapper.updateById(userAuth);
        } else {
            throw new BizException("??????????????????");
        }
    }

    @Override
    public PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition) {
        // ????????????????????????
        Integer count = userAuthMapper.countUser(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // ????????????????????????
        List<UserBackDTO> userBackDTOList = userAuthMapper.listUsers(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(userBackDTOList, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoDTO qqLogin(QQLoginVO qqLoginVO) {
        return socialLoginStrategyContext.executeLoginStrategy(JSON.toJSONString(qqLoginVO), LoginMethodEnum.QQ);
    }

    /**
     * ??????????????????????????????
     *
     * @param user ????????????
     * @return ??????
     */
    private Boolean checkUser(UserVO user) {
        if (!user.getCode().equals(redisService.get(USER_CODE_KEY + user.getUsername()))) {
            throw new BizException("??????????????????");
        }
        //???????????????????????????
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }

    /**
     * ??????????????????
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void statisticalUserArea() {
        // ????????????????????????
        Map<String, Long> userAreaMap = userAuthMapper.selectList(new LambdaQueryWrapper<UserAuth>().select(UserAuth::getIpSource))
                .stream()
                .map(item -> {
                    if (StringUtils.isNotBlank(item.getIpSource())) {
                        return item.getIpSource().substring(0, 2)
                                .replaceAll(PROVINCE, "")
                                .replaceAll(CITY, "");
                    }
                    return UNKNOWN;
                })
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()));
        // ????????????
        List<UserAreaDTO> userAreaList = userAreaMap.entrySet().stream()
                .map(item -> UserAreaDTO.builder()
                        .name(item.getKey())
                        .value(item.getValue())
                        .build())
                .collect(Collectors.toList());
        redisService.set(USER_AREA, JSON.toJSONString(userAreaList));
    }
}




