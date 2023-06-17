package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountVo {
    @ApiModelProperty(value = "账号ID")
    public Integer accountId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "头像")
    public String avatar;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
