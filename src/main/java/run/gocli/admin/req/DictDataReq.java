package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DictDataReq {
    @ApiModelProperty("字典ID")
    private Integer typeId;
}
