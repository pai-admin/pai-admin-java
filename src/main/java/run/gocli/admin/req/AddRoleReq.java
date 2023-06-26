package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddRoleReq {
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @ApiModelProperty("菜单权限")
    private int[] menus;
    @ApiModelProperty("选中的菜单(不包含半选，仅做数据展示用)")
    private int[] checkedMenus;
    @ApiModelProperty("排序")
    private Integer rank;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("角色标识")
    private String flag;
    @ApiModelProperty("备注")
    private String remark;
}
