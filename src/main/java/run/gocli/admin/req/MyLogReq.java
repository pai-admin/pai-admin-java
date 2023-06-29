package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MyLogReq {
    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    private Integer page;
    @ApiModelProperty(value = "页数", required = true)
    @NotNull(message = "页数不能为空")
    private Integer limit;
}
