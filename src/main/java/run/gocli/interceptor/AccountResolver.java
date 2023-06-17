/**
 * Class UserIdResolver
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/11
 */
package run.gocli.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import run.gocli.core.server.RedisService;
import run.gocli.utils.AccountInfo;

public class AccountResolver implements HandlerMethodArgumentResolver {
    private final RedisService redisService;

    private final String tokenKey;

    public AccountResolver(RedisService redisService, String tokenKey) {
        this.redisService = redisService;
        this.tokenKey = tokenKey;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AccountInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //从请求头中获取token
        String token = nativeWebRequest.getHeader("authorization");
        if (token == null || "".equals(token)) {
            throw new RuntimeException("未登录");
        }
        // 从redis中获取管理员id
        return redisService.getObject(tokenKey+token, run.gocli.core.entity.Account.class);
    }
}
