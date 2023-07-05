package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogReq {
    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    private Integer page;
    @ApiModelProperty(value = "页数", required = true)
    @NotNull(message = "页数不能为空")
    private Integer limit;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("操作名称")
    private String title;
}
