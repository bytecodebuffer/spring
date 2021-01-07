package top.handler;

import top.enums.SubCodeEnum;
import top.enums.subcodes.SystemSubCode;
import top.model.response.ResponseInfo;
import top.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author lgs
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseInfo exceptionHandler(Exception exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("未知异常 @类型@：" + exception.getClass().toString(), SystemSubCode.UNKNOWN_EXCEPTION);
        return response;
    }

    /**
     * 运行时异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseInfo runtimeExceptionHandler(RuntimeException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("运行时异常：" + exception.getLocalizedMessage(), SystemSubCode.RUNTIME_EXCEPTION);
        return response;
    }

    /**
     * 空指针异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseInfo nullPointerExceptionHandler(NullPointerException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("-->>空指针异常信息：" + exception.getStackTrace()[0].toString(), SystemSubCode.NULL_POINTER_EXCEPTION);
        return response;
    }

    /**
     * 类型转换异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public ResponseInfo classCastExceptionHandler(ClassCastException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("类型转换异常：" + exception.getLocalizedMessage(), SystemSubCode.CLASS_CAST_EXCEPTION);
        return response;
    }

    /**
     * IO异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ResponseInfo iOExceptionHandler(IOException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("IO异常：" + exception.getLocalizedMessage(), SystemSubCode.IO_EXCEPTION);
        return response;
    }

    /**
     * 数组越界异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseInfo indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException exception) {
        logger.error(exception.getLocalizedMessage());
        return errorResponse("数组越界异常：" + exception.getLocalizedMessage(), SystemSubCode.INDEX_OUT_BOUND_EXCEPTION);
    }

    /**
     * 请求方法不支持
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseInfo request405(HttpRequestMethodNotSupportedException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("请求方法不支持：" + exception.getLocalizedMessage(), SystemSubCode.METHOD_NOT_SUPPORTED_EXCEPTION);
        return response;
    }


    /**
     * 请求参数格式不支持(content-type类型)
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseInfo request406(HttpMediaTypeNotSupportedException exception) {
        logger.error(exception.getLocalizedMessage());
        ResponseInfo response = errorResponse("请求参数格式不支持：" + exception.getLocalizedMessage(), SystemSubCode.MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION);
        return response;
    }

    /**
     * 请求参数无效(1:)参数校验不通过  2:)参数读取失败 3:) 请求参数缺少) 4:)参数校验失败
     *
     * @param exception 异常信息
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, ConstraintViolationException.class})
    public ResponseInfo requestParamNotValid(Exception exception) {
        logger.error(exception.getLocalizedMessage());
        String msg = "请求参数无效";
        // 获取参数校验错误提示信息
        if (exception.getClass() == MethodArgumentNotValidException.class) {
            MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) exception;
            BindingResult result = notValidException.getBindingResult();
            if (result.hasErrors() && null != result.getFieldError()) {
                msg = result.getFieldError().getDefaultMessage();
            }
        }
        ResponseInfo response = errorResponse(msg + "：" + exception.getLocalizedMessage(), SystemSubCode.PARAM_EXCEPTION);
        return response;
    }

    /**
     * 枚举值绑定异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseInfo paramBindException(Exception exception) {
        logger.error(exception.getLocalizedMessage());
        String msg = ((BindException) exception).getAllErrors().get(0).getDefaultMessage();
        if (StringUtils.isBlank(msg)) {
            msg = "枚举参数错误！";
        }
        return errorResponse(msg + "：" + exception.getLocalizedMessage(), SystemSubCode.PARAM_EXCEPTION);
    }

    /**
     * 返回错误消息
     *
     * @param msg
     * @param subCode
     * @return
     */
    private ResponseInfo errorResponse(String msg, SubCodeEnum subCode) {
        logger.error(msg);
        return ResponseInfo.error(msg, subCode);
    }


}
