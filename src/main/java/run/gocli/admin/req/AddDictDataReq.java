package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDictDataReq {
    @ApiModelProperty("项ID")
    private Integer dataId;
    @ApiModelProperty(value = "字典ID")
    public Integer typeId;
    @ApiModelProperty("项名称")
    private String name;
    @ApiModelProperty("键值")
    private String content;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("排序")
    private Integer rank;
}
