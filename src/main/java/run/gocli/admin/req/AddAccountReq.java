package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddAccountReq {
    @ApiModelProperty(value = "账户ID")
    private Integer accountId;
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;
    @ApiModelProperty(value = "登录账户", required = true)
    private String username;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "状态 1正常 0冻结", required = true)
    private Integer status;
    @ApiModelProperty(value = "权限")
    private int[] roles;
}
