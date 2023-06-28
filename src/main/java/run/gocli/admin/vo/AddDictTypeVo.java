package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDictTypeVo {
    @ApiModelProperty(value = "字典ID")
    public Integer typeId;
}
