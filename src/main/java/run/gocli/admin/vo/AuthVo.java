package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AuthVo {
    @ApiModelProperty(value = "按钮")
    public List<String> buttons;
    @ApiModelProperty(value = "角色")
    public List<String> roles;
    @ApiModelProperty(value = "菜单")
    public List<MenuVo> menus;
}
