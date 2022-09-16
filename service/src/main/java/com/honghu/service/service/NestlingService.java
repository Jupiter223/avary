package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.Nestling;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-12
 */
public interface NestlingService extends IService<Nestling> {

    List<Nestling> getPageListEgg(Page<Nestling> page);

    boolean add(Nestling nestling);

    Nestling getOneById(String id);

    boolean update(Nestling nestling);

    List<Nestling> getByParentId(String id);

    List<Nestling> getThisYearByParentId(String id);

    List<Nestling> getByYearByParentId(String id, String year);
}
