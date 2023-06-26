package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleMenuVo {
    @ApiModelProperty(value = "角色Id")
    private Integer roleId;
    @ApiModelProperty(value = "菜单Id")
    private Integer menuId;
}
