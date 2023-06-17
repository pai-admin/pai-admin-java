package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditAccountReq {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "头像")
    public String avatar;
}
