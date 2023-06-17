/**
 * Class Role
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/12
 */
package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "角色表")
@TableName(value = "lea_role")
public class Role {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("选中菜单")
    private String checkedMenus;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("排序")
    private Integer rank;
    @ApiModelProperty("备注")
    private Integer remark;
    @TableLogic
    @ApiModelProperty("删除标识")
    private Integer delFlag;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String updateTime;
}
