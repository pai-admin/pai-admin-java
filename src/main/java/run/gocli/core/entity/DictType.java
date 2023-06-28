package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "lea_dict_type")
@ApiModel(value = "字典分类", description = "字典分类表")
public class DictType {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "字典ID")
    private Integer typeId;

    @ApiModelProperty(value = "上级部门")
    private Integer parentId;

    @ApiModelProperty(value = "字典名称")
    private String typeName;

    @ApiModelProperty(value = "字典编码")
    private String flag;

    @ApiModelProperty(value = "排序")
    private Integer rank;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "删除标识")
    @TableLogic
    private Integer delFlag;
}
