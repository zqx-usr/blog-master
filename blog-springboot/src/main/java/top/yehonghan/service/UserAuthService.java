package top.yehonghan.service;

import top.yehonghan.dto.UserAreaDTO;
import top.yehonghan.dto.UserBackDTO;
import top.yehonghan.dto.UserInfoDTO;
import top.yehonghan.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.*;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_user_auth】的数据库操作Service
* @createDate 2022-12-11 14:40:22
*/
public interface UserAuthService extends IService<UserAuth> {

    /**
     * 获取用户区域分布
     *
     * @param conditionVO 条件签证官
     * @return {@link List <UserAreaDTO>} 用户区域分布
     */
    List<UserAreaDTO> listUserAreas(ConditionVO conditionVO);

    /**
     * 发送邮箱验证码
     *
     * @param username 邮箱号
     */
    void sendCode(String username);

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    void register(UserVO user);

    /**
     * qq登录
     *
     * @param qqLoginVO qq登录信息
     * @return 用户登录信息
     */
    UserInfoDTO qqLogin(QQLoginVO qqLoginVO);

    /**
     * 修改密码
     *
     * @param user 用户对象
     */
    void updatePassword(UserVO user);

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码对象
     */
    void updateAdminPassword(PasswordVO passwordVO);

    /**
     * 查询后台用户列表
     *
     * @param condition 条件
     * @return 用户列表
     */
    PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition);

}
