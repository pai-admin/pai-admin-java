package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LogVo {
    @ApiModelProperty(value = "日志ID")
    private Integer logId;

    @ApiModelProperty(value = "管理员名称")
    private String username;

    @ApiModelProperty(value = "操作名称")
    private String title;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "权限标识")
    private String flag;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "响应状态")
    private Integer code;

    @ApiModelProperty(value = "浏览器标识")
    private String ua;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
