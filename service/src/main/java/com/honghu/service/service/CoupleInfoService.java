package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Card;
import com.honghu.service.entity.CoupleInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honghu.service.vo.CoupleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-10
 */
public interface CoupleInfoService extends IService<CoupleInfo> {

    boolean addList(List<AvaryInfo> infos);

//    List<CoupleVo> getPageListCouple(Page<CoupleVo> page);

    boolean add(CoupleInfo coupleInfo);

    CoupleInfo getOneById(String id);

    List<CoupleVo> getAll();
}
