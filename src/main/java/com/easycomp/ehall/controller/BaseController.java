package com.easycomp.ehall.controller;

import com.easycomp.dto.R;
import com.easycomp.enums.Error;
import com.easycomp.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 公用函数方法
 * @author rensq
 * @create 2019-07-26 15:28
 */
public abstract class BaseController {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected Validator validator;

    /**
     * 快速失败模式: 只要有一个验证失败，则返回
     * 实现校验方式并抛出异常
     * @param obj
     * @return
     */
    protected boolean validate(Object obj) {
        Set<ConstraintViolation<Object>> validateSet = validator.validate(obj);
        Map<String, String> map = new HashMap<>();
        if (!validateSet.isEmpty()) {
            validateSet.forEach((validation) -> {
                String errorMsg = validation.getMessage();
                String propertyName = validation.getPropertyPath().toString();
                map.put(propertyName, errorMsg);
            });
            throw new ApiException(Error.参数异常,map);
        }
        return true;
    }

    /**
     * 返回成功
     */
    protected R success() {
        return R.success();
    }
    protected R ok(Object data) {
        return R.success(data);
    }
    protected R success(String message) {
        return R.success(message);
    }
    protected R ok(Object data, String message) {
        return R.success(data,message);
    }

    /**
     * 返回失败
     */
    protected R error() {
        return R.error();
    }
    protected R error(String msg) {
        return R.error(msg);
    }
    protected R error(int code, String msg) {
        return R.error(code,msg);
    }
    protected R error(Error error) {
        return R.error(error);
    }
    protected R error(Error error, String msg) {
        return R.error(error, msg);
    }
    protected R error(Object data, String message) {
        return R.error(data,message);
    }
    protected R error(Object data, Error error, String message) {
        return R.error(data,error,message);
    }
}