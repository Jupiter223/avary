package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.EggInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-10
 */
public interface EggInfoService extends IService<EggInfo> {

    List<EggInfo> getPageListEgg(Page<EggInfo> page);

    boolean add(EggInfo eggInfo);

    EggInfo getOneById(String id);

    boolean update(EggInfo eggInfo);

    List<EggInfo> getByParentId(String id);

    List<EggInfo> getThisYearByParentId(String id);

    List<EggInfo> getByYearByParentId(String id, String year);
}
