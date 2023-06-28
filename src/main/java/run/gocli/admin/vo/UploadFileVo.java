package run.gocli.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadFileVo {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("路径")
    private String path;
    @ApiModelProperty("文件大小")
    private Long size;
}
