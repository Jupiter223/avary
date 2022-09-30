package com.honghu.service.service;

import com.honghu.service.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honghu.service.vo.StockVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-21
 */
public interface StockService extends IService<Stock> {

    boolean add(Stock stock);

    boolean reduce(Stock stock);

    boolean updateStock(List<StockVo> stockVo);

    boolean updateStockNumber(Stock stock);
}
