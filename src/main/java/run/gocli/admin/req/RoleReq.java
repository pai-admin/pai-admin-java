package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleReq {
    @ApiModelProperty("角色名称")
    private String name;
}
