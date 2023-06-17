/**
 * Class Menu
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
@ApiModel(value = "菜单表", description = "菜单表")
@TableName(value = "lea_menu")
public class Menu {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("菜单id")
    private Integer menuId;
    @ApiModelProperty("上级菜单id")
    private Integer parentId;
    @ApiModelProperty("菜单名称")
    private String title;
    @ApiModelProperty("菜单类型 0菜单 1按钮 2权限")
    private Integer type;
    @ApiModelProperty("请求方式")
    private String method;
    @ApiModelProperty("权限标识")
    private String flag;
    @ApiModelProperty("页面名称")
    private String name;
    @ApiModelProperty("路由地址")
    private String path;
    @ApiModelProperty("菜单icon")
    private String icon;
    @ApiModelProperty("菜单icon")
    private Integer rank;
    @ApiModelProperty("隐藏路由")
    private Integer hidden;
    @TableLogic
    @ApiModelProperty("删除标识")
    private Integer delFlag;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String updateTime;
    @ApiModelProperty("备注")
    private String remark;
}
