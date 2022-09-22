package com.honghu.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class StockVo {
    private String id;

    private String itemId;

    private String name;

    private BigDecimal price;

    private String brand;

    private Float weight;

    private String unit;

    private Integer stockNumber;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;
}
