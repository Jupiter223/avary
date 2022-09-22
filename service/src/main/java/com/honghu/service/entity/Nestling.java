package com.honghu.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Nestling对象", description="")
public class Nestling implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @NotEmpty(message = "必须指定亲鸟")
    private String parentId;

    private String parentLocation;

    private String parentNickname;

    private String speices;
    @Min(value=1,message = "窝数必须大于等于1")
    private Integer nest;
    @Min(value=1,message = "个数必须大于等于1")
    private Integer count;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    private String otherInfo;

    private Integer isDead;

    private Integer isOut;

    private Integer isTransfer;

    private String gender;

    private Integer ring;

    private String pic;


}
