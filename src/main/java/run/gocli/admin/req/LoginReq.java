package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginReq {
    @ApiModelProperty(value = "验证码ID", required=true)
    protected String verifyId;
    @ApiModelProperty(value = "验证码", required=true)
    protected String verifyCode;
    @ApiModelProperty(value = "用户名", required=true)
    protected String username;
    @ApiModelProperty(value = "密码", required=true)
    protected String password;
}
