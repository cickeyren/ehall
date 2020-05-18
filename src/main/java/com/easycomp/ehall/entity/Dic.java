package com.easycomp.ehall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表; InnoDB free: 8192 kB
 * </p>
 *
 * @author rensq
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dic")
@ApiModel(value="Dic对象", description="字典表; InnoDB free: 8192 kB")
public class Dic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "代码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型代码")
    private String typeId;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "备注")
    private String remark;


}
