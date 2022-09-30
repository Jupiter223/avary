package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.StockItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honghu.service.vo.StockVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-20
 */
public interface StockItemService extends IService<StockItem> {

    List<StockVo> getPage(long current, long limit);

    List<StockItem> getPageList(Page<StockItem> page);

    boolean add(StockItem stockItem);

    boolean update(StockItem stockItem);

    StockItem getOneById(String id);
}
