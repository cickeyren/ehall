package com.easycomp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用参数对象
 * @author rensq
 * @create 2020-02-18
 */
@Data
@ApiModel(value="通用参数对象")
public class GeneralParam implements Serializable {
	//序列化
	private static final long serialVersionUID = 1L;

}