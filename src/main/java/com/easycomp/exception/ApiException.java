package com.easycomp.exception;

import cn.hutool.core.util.StrUtil;
import com.easycomp.enums.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * API自定义异常
 * @author rensq
 * @create 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException{

    private static final long serVersionUID = 1L;

    private int code = 1;

    private Error error;

    private String message;

    private Map<String,String> errMap = new HashMap<>();

    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.code = httpStatus.value();
    }
    public ApiException(Error error) {
        super(error.getErrMsg());
        this.code = error.getCode();
        this.message = error.getErrMsg();
    }
    public ApiException(Error error, String msg) {
        super(msg);
        this.code = error.getCode();
        this.message = error.getErrMsg() + "," + msg;
    }
    public ApiException(Error error, Map<String,String> errMap) {
        super(error.getErrMsg());
        this.code = error.getCode();
        String errorMsg = StrUtil.join(",",errMap.values().toArray()); //返回错误信息
        this.message = error.getErrMsg() + "," + errorMsg;
    }
}