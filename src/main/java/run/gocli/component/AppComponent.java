package run.gocli.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppComponent {
    private String tokenKey;
    private String secret;
    private Long tokenTtl;
    private Long verifyTtl;
    private String path;
}
