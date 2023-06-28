package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DictTypeListVo {
    @ApiModelProperty("字典ID")
    private Integer typeId;
    @ApiModelProperty("字典名称")
    private String typeName;
    @ApiModelProperty(value = "上级ID")
    public Integer parentId;
    @ApiModelProperty("字典编码")
    private String flag;
    @ApiModelProperty("排序")
    private Integer rank;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String updateTime;
}
