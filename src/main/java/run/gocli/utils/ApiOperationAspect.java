/**
 * Class ApiOperationAspectService
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/15
 */
package run.gocli.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import run.gocli.component.AppComponent;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;
import run.gocli.core.server.IAccountLogService;
import run.gocli.core.server.RedisService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class ApiOperationAspect {
    @Autowired
    private IAccountLogService accountLogService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AppComponent appComponent;
    /**
     * [@Pointcut]：统一切点,对com.example.controller及其子包中所有的类的所有方法切面
     */
    @Pointcut("@annotation(run.gocli.utils.AuthPermission)")
    public void pointcut() {}

    /**
     * [@Around]：环绕通知
     */
    @Around("pointcut()&&@annotation(auth)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, AuthPermission auth) throws Throwable {
        Object result = joinPoint.proceed();
        if (Objects.equals(auth.auth(), "*")) return result;
        // 无需登录的和无需权限的不用记录日志
        if (!auth.needAuth() || !auth.needLogin()) return result;
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 操作人
        String token = request.getHeader("authorization");
        Account account = redisService.getObject(appComponent.getTokenKey()+token, Account.class);
        // 记录日志
        AccountLog logData = new AccountLog();
        logData.setAccountId(account.getAccountId());
        logData.setUsername(account.getUsername());
        logData.setTitle("");
        logData.setMethod(request.getMethod().toLowerCase(Locale.ROOT));
        logData.setFlag(auth.auth());
        logData.setIp(StrUtil.getIpAddress(request));
        try {
            R<Object> res = (R<Object>) result;
            // 状态
            logData.setCode(res.getCode());
        }catch (Exception e) {
            logData.setCode(500);
        }
        logData.setUa(request.getHeader("user-agent"));
        logData.setResponse(result.toString());
        logData.setRequest("");
        logData.setCreateTime(DateUtil.getCurrentDateTime(null,0));
        accountLogService.save(logData);
        return result;
    }
}
