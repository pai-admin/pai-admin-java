package run.gocli.admin.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDictTypeReq {
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
}
