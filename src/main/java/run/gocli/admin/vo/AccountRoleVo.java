/**
 * Class AdminRoleVo
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/12
 */
package run.gocli.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员角色")
public class AccountRoleVo {
    @ApiModelProperty(value = "角色Id")
    private Integer roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "账号Id")
    private Integer accountId;
}
