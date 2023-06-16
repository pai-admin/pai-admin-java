package run.gocli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/admin/**"))
                .build()
                .groupName("admin");
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder().title("管理端接口文档")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(userApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .groupName("user");
    }

    private ApiInfo userApiInfo() {
        return new ApiInfoBuilder().title("用户端接口文档")
                .version("1.0")
                .build();
    }
}
