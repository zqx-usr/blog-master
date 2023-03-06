package top.yehonghan.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yehonghan.service.SysLogService;
import top.yehonghan.entity.SysLog;
import top.yehonghan.exception.CustomException;
import top.yehonghan.util.ByteUtils;
import top.yehonghan.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

import static top.yehonghan.constant.CommonConst.SYS_LOG_ERROR;
import static top.yehonghan.constant.CommonConst.SYS_LOG_INFO;

/**
 * ClassName: SysLogAspect
 * Package: top.yehonghan.aspect
 * Description:
 *
 * @Author: yehonghan
 * @Create: 2022/12/10 14:00
 * @Version: V1.0
 */
@Log4j2
@Aspect
@Component
public class SysLogAspect {

    private ThreadLocal<SysLog> sysLogThreadLocal = new ThreadLocal<>();

    @Autowired
    private Executor SysLogThreadPoolTaskExecutor;

    @Autowired
    private SysLogService sysLogService;

    @Value("${log.isOpen}")
    private String isOpen;

    /**
     * 日志切点
     */
    @Pointcut("execution(public * top.yehonghan.controller.*.*(..))")
    public void sysLogAspect() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before(value = "sysLogAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        SysLog sysLog = new SysLog();
        // 创建人信息请根据实际项目获取方式获取
        sysLog.setCreateUserCode("yhh");
        sysLog.setCreateUserName("yhh");
        sysLog.setStartTime(LocalDateTime.now());
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setRequestParams(formatParams(request.getParameterMap()));
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setRequestIp(ServletUtil.getClientIP(request));
        sysLog.setServerAddress(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
        String userAgentStr = request.getHeader("User-Agent");
        sysLog.setUserAgent(userAgentStr);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        sysLog.setDeviceName(userAgent.getOs().getName());
        sysLog.setBrowserName(userAgent.getBrowser().getName());

        sysLogThreadLocal.set(sysLog);
        if(SYS_LOG_INFO.equals(isOpen)){
            log.info("开始计时: {}  URI: {}  IP: {}", sysLog.getStartTime(), sysLog.getRequestUri(), sysLog.getRequestIp());
        }

    }

    /**
     * 返回通知
     *
     * @param ret
     */
    @AfterReturning(pointcut = "sysLogAspect()", returning = "ret")
    public void doAfterReturning(Object ret) {
        if(SYS_LOG_INFO.equals(isOpen)){
            SysLog sysLog = sysLogThreadLocal.get();
            sysLog.setLogType("INFO");
            sysLog.setEndTime(LocalDateTime.now());
            sysLog.setExecuteTime(Math.toIntExact(ChronoUnit.MILLIS.between(sysLog.getStartTime(), sysLog.getEndTime())));
            Result<?> r = Convert.convert(Result.class, ret);
            sysLog.setIsException("NO");
            SysLogThreadPoolTaskExecutor.execute(new SaveLogThread(sysLog, sysLogService));
            sysLogThreadLocal.remove();

            Runtime runtime = Runtime.getRuntime();
            log.info("计时结束: {}  用时: {}ms  URI: {}  总内存: {}  已用内存: {}", sysLog.getEndTime(), sysLog.getExecuteTime(),
                    sysLog.getRequestUri(), ByteUtils.formatByteSize(runtime.totalMemory()),
                    ByteUtils.formatByteSize(runtime.totalMemory() - runtime.freeMemory()));
        }
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Exception e) {
        if(SYS_LOG_INFO.equals(isOpen)||SYS_LOG_ERROR.equals(isOpen)){
            SysLog sysLog = sysLogThreadLocal.get();
            sysLog.setLogType("ERROR");
            sysLog.setEndTime(LocalDateTime.now());
            sysLog.setExecuteTime(Math.toIntExact(ChronoUnit.MINUTES.between(sysLog.getStartTime(), sysLog.getEndTime())));
            sysLog.setIsException("YES");
            // 定位报错位置
            StringBuilder sbException = new StringBuilder();
            StackTraceElement element=e.getStackTrace()[0];
            sbException.append(MessageFormat.format("{0}.{1}({2}:{3})",
                    element.getClassName(), element.getMethodName(), element.getFileName(), element.getLineNumber()));
            String projectName = System.getProperty("user.dir");
            sysLog.setExceptionInfo(projectName+" : "+sbException +" : "+e.getMessage());
            SysLogThreadPoolTaskExecutor.execute(new SaveLogThread(sysLog, sysLogService));
            sysLogThreadLocal.remove();

            Runtime runtime = Runtime.getRuntime();
            log.info("计时结束: {}  用时: {}ms  URI: {}  总内存: {}  已用内存: {}", sysLog.getEndTime(), sysLog.getExecuteTime(),
                    sysLog.getRequestUri(), ByteUtils.formatByteSize(runtime.totalMemory()),
                    ByteUtils.formatByteSize(runtime.totalMemory() - runtime.freeMemory()));
        }
    }

    /**
     * 格式化参数
     *
     * @param parameterMap
     * @return
     */
    private String formatParams(Map<String, String[]> parameterMap) {
        if (parameterMap == null) {
            return null;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : (parameterMap).entrySet()) {
            if (params.length() != 0) {
                params.append("&");
            }
            params.append(param.getKey() + "=");
            if (StrUtil.endWithIgnoreCase(param.getKey(), "password")) {
                params.append("*");
            } else if (param.getValue() != null) {
                params.append(ArrayUtil.join(param.getValue(), ","));
            }
        }
        return params.toString();
    }

    /**
     * 保存日志线程
     */
    private static class SaveLogThread extends Thread {
        private SysLog sysLog;
        private SysLogService sysLogService;

        public SaveLogThread(SysLog sysLog, SysLogService sysLogService) {
            this.sysLog = sysLog;
            this.sysLogService = sysLogService;
        }

        @Override
        public void run() {
            sysLog.setCreateDate(LocalDateTime.now());
            sysLogService.save(sysLog);
        }
    }
}
