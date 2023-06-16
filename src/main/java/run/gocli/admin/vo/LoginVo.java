package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVo {
    @ApiModelProperty(value = "登录凭证")
    public String token;
}
