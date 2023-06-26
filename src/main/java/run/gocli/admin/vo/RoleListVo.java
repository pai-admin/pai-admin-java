package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleListVo {
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色权限")
    private List<RoleMenuVo> menuVos;
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
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String updateTime;
}
