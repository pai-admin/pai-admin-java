package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeptReq {
    @ApiModelProperty("部门名称")
    private String deptName;
}
