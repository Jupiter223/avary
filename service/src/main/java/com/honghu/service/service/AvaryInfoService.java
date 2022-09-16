package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
public interface AvaryInfoService extends IService<AvaryInfo> {

    boolean add(AvaryInfo avaryInfo);

    List<AvaryInfo> getPageListAvary(Page<AvaryInfo> page);

    AvaryInfo getOneById(String id);

    boolean update(AvaryInfo avaryInfo);

    List<AvaryInfo> getOneByCoupleId(String id);
}
