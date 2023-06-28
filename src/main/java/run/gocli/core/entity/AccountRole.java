package run.gocli.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "lea_account_role")
@ApiModel(value = "账号角色", description = "账号角色表")
public class AccountRole {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty("账号id")
    private Integer accountId;
    @ApiModelProperty("角色id")
    private Integer roleId;
}
