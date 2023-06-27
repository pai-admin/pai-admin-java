package run.gocli.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddDeptReq {
    @ApiModelProperty("角色id")
    private Integer deptId;
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String deptName;
    @ApiModelProperty(value = "上级ID")
    public Integer parentId;
    @ApiModelProperty("排序")
    private Integer rank;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("备注")
    private String remark;
}
