package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeptListVo {
    @ApiModelProperty("部门id")
    private Integer deptId;
    @ApiModelProperty("部门名称")
    private String deptName;
    @ApiModelProperty(value = "上级ID")
    public Integer parentId;
    @ApiModelProperty("排序")
    private Integer rank;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String updateTime;
}
