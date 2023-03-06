package top.yehonghan.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.yehonghan.enums.StatusCodeEnum;
import top.yehonghan.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static top.yehonghan.constant.CommonConst.APPLICATION_JSON;

/**
 * ClassName: AuthenticationEntryPointImpl
 * Description: 用户未登录处理方式
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 10:12
 * @Version: V1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.fail(StatusCodeEnum.NO_LOGIN)));
    }
}
