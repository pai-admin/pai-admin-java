package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuVo {
    @ApiModelProperty(value = "菜单名称")
    public String title;
    @ApiModelProperty(value = "页面名称")
    public String name;
    @ApiModelProperty(value = "图标")
    public String icon;
    @ApiModelProperty(value = "页面路径")
    public String path;
    @ApiModelProperty(value = "是否隐藏")
    public Integer hidden;
    @ApiModelProperty(value = "菜单ID")
    public Integer menuId;
    @ApiModelProperty(value = "上级ID")
    public Integer parentId;
}
