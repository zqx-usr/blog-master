package top.yehonghan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.yehonghan.dto.CommentCountDTO;
import top.yehonghan.dto.TalkBackDTO;
import top.yehonghan.dto.TalkDTO;
import top.yehonghan.entity.Talk;
import top.yehonghan.exception.BizException;
import top.yehonghan.mapper.CommentMapper;
import top.yehonghan.service.RedisService;
import top.yehonghan.service.TalkService;
import top.yehonghan.mapper.TalkMapper;
import org.springframework.stereotype.Service;
import top.yehonghan.util.*;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;
import top.yehonghan.vo.TalkVO;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static top.yehonghan.constant.RedisPrefixConst.TALK_LIKE_COUNT;
import static top.yehonghan.constant.RedisPrefixConst.TALK_USER_LIKE;
import static top.yehonghan.enums.TalkStatusEnum.PUBLIC;

/**
* @author 86152
* @description 针对表【tb_talk】的数据库操作Service实现
* @createDate 2023-01-05 11:04:31
*/
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService{
    @Autowired
    private TalkMapper talkMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public List<String> listHomeTalks() {
        // 查询最新10条说说
        return talkMapper.selectList(new LambdaQueryWrapper<Talk>()
                        .eq(Talk::getStatus, PUBLIC.getStatus())
                        .orderByDesc(Talk::getIsTop)
                        .orderByDesc(Talk::getId)
                        .last("limit 10"))
                .stream()
                .map(item -> item.getContent().length() > 200 ? HtmlUtils.deleteHMTLTag(item.getContent().substring(0, 200)) : HtmlUtils.deleteHMTLTag(item.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<TalkDTO> listTalks() {
        // 查询说说总量
        Integer count = Math.toIntExact(talkMapper.selectCount((new LambdaQueryWrapper<Talk>()
                .eq(Talk::getStatus, PUBLIC.getStatus()))));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询说说
        List<TalkDTO> talkDTOList = talkMapper.listTalks(PageUtils.getLimitCurrent(), PageUtils.getSize());
        // 查询说说评论量
        List<Integer> talkIdList = talkDTOList.stream()
                .map(TalkDTO::getId)
                .collect(Collectors.toList());
        Map<Integer, Integer> commentCountMap = commentMapper.listCommentCountByTopicIds(talkIdList)
                .stream()
                .collect(Collectors.toMap(CommentCountDTO::getId, CommentCountDTO::getCommentCount));
        // 查询说说点赞量
        Map<String, Object> likeCountMap = redisService.hGetAll(TALK_LIKE_COUNT);
        talkDTOList.forEach(item -> {
            item.setLikeCount((Integer) likeCountMap.get(item.getId().toString()));
            item.setCommentCount(commentCountMap.get(item.getId()));
            // 转换图片格式
            if (Objects.nonNull(item.getImages())) {
                item.setImgList(CommonUtils.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResult<>(talkDTOList, count);
    }

    @Override
    public TalkDTO getTalkById(Integer talkId) {
        // 查询说说信息
        TalkDTO talkDTO = talkMapper.getTalkById(talkId);
        if (Objects.isNull(talkDTO)) {
            throw new BizException("说说不存在");
        }
        // 查询说说点赞量
        talkDTO.setLikeCount((Integer) redisService.hGet(TALK_LIKE_COUNT, talkId.toString()));
        // 转换图片格式
        if (Objects.nonNull(talkDTO.getImages())) {
            talkDTO.setImgList(CommonUtils.castList(JSON.parseObject(talkDTO.getImages(), List.class), String.class));
        }
        return talkDTO;
    }

    @Override
    public void saveTalkLike(Integer talkId) {
        // 判断是否点赞
        String talkLikeKey = TALK_USER_LIKE + UserUtils.getLoginUser().getUserInfoId();
        if (redisService.sIsMember(talkLikeKey, talkId)) {
            // 点过赞则删除说说id
            redisService.sRemove(talkLikeKey, talkId);
            // 说说点赞量-1
            redisService.hDecr(TALK_LIKE_COUNT, talkId.toString(), 1L);
        } else {
            // 未点赞则增加说说id
            redisService.sAdd(talkLikeKey, talkId);
            // 说说点赞量+1
            redisService.hIncr(TALK_LIKE_COUNT, talkId.toString(), 1L);
        }
    }

    @Override
    public void saveOrUpdateTalk(TalkVO talkVO) {
        Talk talk = BeanCopyUtils.copyObject(talkVO, Talk.class);
        talk.setUserId(UserUtils.getLoginUser().getUserInfoId());
        this.saveOrUpdate(talk);
    }

    @Override
    public void deleteTalks(List<Integer> talkIdList) {
        talkMapper.deleteBatchIds(talkIdList);
    }

    @Override
    public PageResult<TalkBackDTO> listBackTalks(ConditionVO conditionVO) {
        // 查询说说总量
        Integer count = Math.toIntExact(talkMapper.selectCount(new LambdaQueryWrapper<Talk>()
                .eq(Objects.nonNull(conditionVO.getStatus()), Talk::getStatus, conditionVO.getStatus())));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询说说
        List<TalkBackDTO> talkDTOList = talkMapper.listBackTalks(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        talkDTOList.forEach(item -> {
            // 转换图片格式
            if (Objects.nonNull(item.getImages())) {
                item.setImgList(CommonUtils.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResult<>(talkDTOList, count);
    }

    @Override
    public TalkBackDTO getBackTalkById(Integer talkId) {
        TalkBackDTO talkBackDTO = talkMapper.getBackTalkById(talkId);
        // 转换图片格式
        if (Objects.nonNull(talkBackDTO.getImages())) {
            talkBackDTO.setImgList(CommonUtils.castList(JSON.parseObject(talkBackDTO.getImages(), List.class), String.class));
        }
        return talkBackDTO;
    }
}




