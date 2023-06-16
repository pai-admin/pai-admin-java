package run.gocli.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import run.gocli.component.AppComponent;
import run.gocli.core.server.RedisService;
import run.gocli.utils.AuthPermission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AppComponent appComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception e) {
            return true;
        }
        AuthPermission anno = null;
        Map<String, Object> map = new HashMap<>();
        anno = handlerMethod.getMethodAnnotation(AuthPermission.class);
        // 没有注解直接放行
        if (anno == null) {
            return true;
        }
        // 不需要登录的话直接放行
        if (!anno.needLogin()) {
            return true;
        }
        String token = request.getHeader("authorization");
        // 请求头没有携带token 拦截
        if (token == null) {
            map.put("code", 401);
            map.put("msg", "登录失效");
            response(response, map);
            return false;
        }
        // 从redis中获取管理员id
        String id = redisService.get(appComponent.getTokenKey() + token);
        // 登录失效 拦截
        if (id == null) {
            map.put("code", 401);
            map.put("msg", "登录失效");
            response(response, map);
            return false;
        }

        // token续签
        redisService.add(appComponent.getTokenKey() + token, Integer.parseInt(id), 8, TimeUnit.HOURS);
        // 无需权限验证的话直接放行
        if (!anno.needAuth() || anno.auth().equals("*")) {
            return true;
        }
        // 日志
        String tag = request.getMethod().toLowerCase(Locale.ROOT) + ":" + anno.auth();
        log.info("操作日志：管理员=" + id + " 权限=" + tag);

        // 当前操作权限
        List<String> auth = redisService.getList(appComponent.getTokenKey() + token, String.class);
        if (auth.contains(tag)) {
            return true;
        }

        // 权限不足
        map.put("code", 403);
        map.put("msg", "权限不足");
        response(response, map);
        return false;
    }

    // 拦截响应
    private void response(HttpServletResponse response, Map<String, Object> map) {
        try {
            response.setContentType("application/json; charset=utf-8");
            String json = new ObjectMapper().writeValueAsString(map);
            response.getWriter().println(json);
        } catch (Exception ignored) {
        }
    }
}