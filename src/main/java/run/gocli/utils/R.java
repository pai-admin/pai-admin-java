package run.gocli.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {
    public static final Integer SUCCESS = 200;
    public static final String SUCCESS_MSG = "success";

    public static final Integer ERROR = 400;
    public static final String ERROR_MSG = "error";

    @ApiModelProperty(value = "状态")
    private Integer code;
    @ApiModelProperty(value = "信息")
    private String msg;
    @ApiModelProperty(value = "内容")
    private T data;
    @ApiModelProperty(value = "数量")
    private Long count;

    public R(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    // 成功返回
    public static <E> R<E> success(E data){
        return new R<E>(R.SUCCESS, R.SUCCESS_MSG, data);
    }

    // 失败返回
    public static <E> R<E> error(String msg) {
        return new R<E>(R.ERROR, msg, null);
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public R<T> count(Long count) {
        this.count = count;
        return this;
    }
}