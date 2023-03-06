package top.yehonghan.util;

import org.springframework.security.core.context.SecurityContextHolder;
import top.yehonghan.entity.UserDetailsImpl;

/**
 * ClassName: UserUtils
 * Description: 获取登录用户信息
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 10:17
 * @Version: V1.0
 */
public class UserUtils {
    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailsImpl getLoginUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
