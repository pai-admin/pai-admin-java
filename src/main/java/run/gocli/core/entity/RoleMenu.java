/**
 * Class RoleMenu
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/12
 */
package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "角色菜单表")
@TableName(value = "lea_role_menu")
public class RoleMenu {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("菜单id")
    private Integer menuId;
}