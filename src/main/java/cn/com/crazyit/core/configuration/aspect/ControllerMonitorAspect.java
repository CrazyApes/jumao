package cn.com.crazyit.core.configuration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Aspect
@Component
public final class ControllerMonitorAspect {

    private final Logger LOG = LoggerFactory.getLogger("控制器跟踪");

    @Pointcut("execution(public * cn.com.crazyit.web.controller..*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        {
            LOG.info("********************************************************************************");
            LOG.info("请求地址 : {}", request.getRequestURL().toString());
            LOG.info("请求方法 : {}", request.getMethod());
            LOG.info("请求IP : {}", request.getRemoteAddr());
            LOG.info("执行方法 : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            LOG.info("参数信息 : {}", Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "result", pointcut = "pointCut()")
    public void doAfter(Object result) {
        LOG.info("返回结果 : {}", result.toString());
        LOG.info("********************************************************************************");
    }
}
