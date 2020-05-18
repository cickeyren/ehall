package com.easycomp.exception;

import com.easycomp.dto.R;
import com.easycomp.enums.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统一处理异常
 * @author rensq
 * @create 2020-02-18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义API异常
     */
    @ExceptionHandler(ApiException.class)
    public R handleApiException(ApiException e) {
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleMRPException(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.参数绑定异常, e.getMessage());
    }

    /**
     * GET,POST 等方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleRRException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.请求方式错误, "请求方式不对，试试 /GET/POST/PUT...");
    }


    /**
     * 数据库异常
     * DataIntegrityViolationException=违反了主键唯一性限制
     * UncategorizedSQLException字段类型错误或者表字段重复
     */
    @ExceptionHandler({DataIntegrityViolationException.class,UncategorizedSQLException.class})
    public R handleSQLiteException(Exception e) {
        if (e instanceof DataIntegrityViolationException || e instanceof UncategorizedSQLException) {
            logger.error(e.getMessage(), e);
            return R.error(Error.数据库操作异常, "字段类型错误、表字段重复或者违反了主键唯一性规则等");
        }
        return handleException(e);
    }

    /**
     * 传入参数校验异常
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBindException(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
        logger.error(msg,exception);
        //return R.error(Error.参数异常,msg);
        return R.error(getErrors(bindResult), Error.参数异常,msg);
    }

    /**
     * get请求参数校验异常
     */
    @ExceptionHandler(value = {ValidationException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleValidationException(Exception e) {
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                logger.error(item.getMessage(), e);
                //获取错误信息
                return R.error(getErrors(violations), Error.参数异常,"请求参数不合法");
            }
        }
        return R.error();
    }

    /**
     * 路径异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(NoHandlerFoundException e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.请求路径异常, "路径不存在，请检查路径是否正确");
    }

    /**
     * 输入/输出异常
     */
    @ExceptionHandler(IOException.class)
    public R handleIOException(IOException e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.输入输出异常, e.getMessage());
    }


    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.系统运行异常, "服务器错误");
    }

    /**
     * 异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return R.error(Error.系统异常);
    }

    /**
     * 获取错误信息
     **/
    private Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        allErrors.forEach(fieldError ->
                map.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return map;
    }

    /**
     * 获取错误信息
     **/
    private Map<String, String> getErrors(Set<ConstraintViolation<?>> violations) {
        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> item : violations) {
            map.put(item.getPropertyPath().toString(), item.getMessage());
        }
        return map;
    }
}
