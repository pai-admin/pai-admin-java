package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddMenuVo {
    @ApiModelProperty(value = "菜单ID")
    public Integer menuId;
}
