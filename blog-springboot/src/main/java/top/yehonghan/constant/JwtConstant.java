package top.yehonghan.constant;

/**
 * ClassName: JwtConstant
 * Package: top.yehonghan.common
 * Description: 系统JWT静态常量
 *
 * @Author: yehonghan
 * @Create: 2022/12/11 16:15
 * @Version: V1.0
 */
public class JwtConstant {
    /**
     * token不存在
     */
    public static final Integer JWT_ERRCODE_NULL=4000;
    /**
     * token过期
     */
    public static final Integer JWT_ERRCODE_EXPIRE=4001;
    /**
     * token验证不通过
     */
    public static final Integer JWT_ERRCODE_FAIL=4002;

    /**
     * 密匙
     */
    public static final  String JWT_SECERT="8677df7fc3a34e26a61c034d5ec8245d";
    /**
     * token 有效时间
     */
    public static final long JWT_TTL=24*60*60*1000;
}
