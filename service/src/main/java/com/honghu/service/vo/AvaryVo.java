package com.honghu.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class AvaryVo {

    private String ring;

    private String location;

    private String year;

    private String gender;

    private String species;

    private Integer isDead;

    private Integer isOut;

    private String nickname;


}
