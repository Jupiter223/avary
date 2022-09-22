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
 * @since 2022-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EggInfo对象", description="")
public class EggInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @NotEmpty(message = "必须指定亲鸟")
    private String parentId;

    private String parentNickname;

    private String parentLocation;

    private String species;
    @Min(value=1,message = "窝数必须大于等于1")
    private Integer nest;
    @Min(value=1,message = "个数必须大于等于1")
    private Integer count;

    private boolean fertilization;

    private boolean hatch;

    private String otherInfo;

    private String pic;

    private Date birthday;

    private Date hatchDate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getBirthday() {
        return birthday;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getHatchDate() {
        return hatchDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public void setHatchDate(Date hatchDate) {
        this.hatchDate = hatchDate;
    }


}
