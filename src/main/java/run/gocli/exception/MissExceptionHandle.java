package run.gocli.exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import run.gocli.utils.R;

// 路由不存在
@ControllerAdvice
public class MissExceptionHandle {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public R<String> ConstraintViolationExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return R.error("接口地址不存在");
    }
}
