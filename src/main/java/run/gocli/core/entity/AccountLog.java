package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "lea_account")
@ApiModel(value = "日志", description = "日志表")
public class AccountLog {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "日志ID")
    private Integer logId;

    @ApiModelProperty(value = "管理员Id")
    private Integer accountId;

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

    @ApiModelProperty(value = "请求参数")
    private String request;

    @ApiModelProperty(value = "响应数据")
    private String response;

    @ApiModelProperty(value = "浏览器标识")
    private String ua;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
