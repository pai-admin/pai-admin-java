package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountListVo {
    @ApiModelProperty(value = "账户ID")
    private Integer accountId;
    @ApiModelProperty(value = "登录账户")
    private String username;
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;
    @ApiModelProperty(value = "部门")
    private String deptName;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "状态 1正常 0冻结")
    private Integer status;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "所属角色")
    private List<AccountRoleVo> roles;
}
