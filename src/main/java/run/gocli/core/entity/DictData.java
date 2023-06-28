package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "lea_dict_data")
@ApiModel(value = "字典项目", description = "字典项目表")
public class DictData {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "项目ID")
    private Integer dataId;

    @ApiModelProperty(value = "字典ID")
    private Integer typeId;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "键值")
    private String content;

    @ApiModelProperty(value = "状态")
    private Integer status;

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
