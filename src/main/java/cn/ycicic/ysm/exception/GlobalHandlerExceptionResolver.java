package cn.ycicic.ysm.exception;

import cn.ycicic.ysm.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.message.AuthException;
import java.util.List;

/**
 * 全局异常处理器
 *
 * @author ycc
 */
@ControllerAdvice
@Slf4j
public class GlobalHandlerExceptionResolver {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<Object> allExceptionHandler(Exception e) {
        log.error(e.getMessage(),e);
        if (e instanceof AuthException) {
            return new ResponseResult<>(e.getMessage(), 403);
        } else if (e instanceof MethodArgumentNotValidException){
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            StringBuffer errorMsg =new StringBuffer();
            allErrors.forEach(objectError -> {
                errorMsg.append(objectError.getDefaultMessage()).append(";");
            });
            return new ResponseResult<>(errorMsg.toString(), 401);
        } else {
            return new ResponseResult<>(e.getMessage(), 500);
        }
    }
}
