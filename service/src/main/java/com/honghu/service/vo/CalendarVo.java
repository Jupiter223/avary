package com.honghu.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CalendarVo {
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    Date date;
}
