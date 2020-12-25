package top.exception;


import top.model.response.RespResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author bz
 * @date 2020/9/19
 *
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler({BindException.class})
    public RespResult validationException(BindException exception){
        List<ObjectError> errors =  exception.getAllErrors();
        if(!CollectionUtils.isEmpty(errors)){
            StringBuilder sb = new StringBuilder();
            errors.forEach(e->sb.append(e.getDefaultMessage()).append(","));
            return new RespResult(400, sb.toString());
        }
        return new RespResult(500, exception.getLocalizedMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public RespResult constraintViolationException(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder sb = new StringBuilder();
            constraintViolations.forEach(e->sb.append(e.getMessage()).append(","));
            return new RespResult(400, sb.toString());
        }
        return new RespResult(500, exception.getLocalizedMessage());
    }

}
