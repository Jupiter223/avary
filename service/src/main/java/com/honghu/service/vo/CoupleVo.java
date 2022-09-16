package com.honghu.service.vo;

import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import lombok.Data;

import java.util.List;
@Data
public class CoupleVo {
    private String id;
    private List<AvaryInfo> parentList;
//    private List<EggInfo> eggList;
//    private List<Nestling> nestlingList;
    private int eggTotal;
    private int eggThisYear;
    private int nestlingTotal;
    private int nestlingThisYear;
    private List<AvaryInfo> list;
    private String otherInfo;
}
