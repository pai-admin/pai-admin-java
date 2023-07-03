package run.gocli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import run.gocli.component.AppComponent;
import run.gocli.core.server.RedisService;
import run.gocli.interceptor.AccountResolver;
import run.gocli.interceptor.CrossOriginInterceptor;
import run.gocli.interceptor.JwtTokenInterceptor;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AppComponent appComponent;
    @Resource
    private CrossOriginInterceptor crossOriginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 跨域
        registry.addInterceptor(crossOriginInterceptor).addPathPatterns("/**");
        // token
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**")
                .excludePathPatterns("/upload/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**")
                .addResourceLocations("classpath:/static/api/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + appComponent.getPath() + "upload" + File.separator);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AccountResolver(redisService, appComponent.getTokenKey()));
    }
}
