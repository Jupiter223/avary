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

/**
 * <p>
 * 
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AvaryInfo对象", description="")
public class AvaryInfo implements Serializable {

//    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String ring;

    private String location;

    private Date birthday;

    private String gender;

    private String species;

    private boolean death;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date deadDate;

    private boolean outStatus;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date outDate;

    private String otherInfo;

    private String cardPic;

    private String avaryPic;

    private String nickname;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getBirthday() {
        return birthday;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String coupleId;
    private String parentId;


}
