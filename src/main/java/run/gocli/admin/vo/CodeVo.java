package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodeVo {
    @ApiModelProperty(value = "验证码ID")
    public String verifyId;
    @ApiModelProperty(value = "base64验证码")
    public String base64Content;
}
