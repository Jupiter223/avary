package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honghu.service.entity.Stock;
import com.honghu.service.entity.StockChange;
import com.honghu.service.mapper.StockMapper;
import com.honghu.service.service.StockChangeService;
import com.honghu.service.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.vo.StockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-21
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
    @Autowired
    private StockChangeService stockChangeService;

    @Override
    public boolean add(Stock stock) {
        StockChange stockChange=new StockChange();
        stockChange.setCreatedDate(new Date());
        stockChange.setItemId(stock.getItemId());
        stockChange.setNumber(stock.getStockNumber());
        stock.setCreateDate(new Date());

        //查找库存中有无该item
        Stock stock1 = baseMapper.selectOne(new QueryWrapper<Stock>().eq("item_id", stock.getItemId()));
        if (stock1==null){
            baseMapper.insert(stock);
        }else{
           int number=stock1.getStockNumber()+stock.getStockNumber();
            stock1.setStockNumber(number);
            baseMapper.updateById(stock1);
        }
        stockChangeService.save(stockChange);

        return true;
    }

    @Override
    public boolean reduce(Stock stock) {
        StockChange stockChange=new StockChange();
        stockChange.setCreatedDate(new Date());
        stockChange.setItemId(stock.getItemId());
        stockChange.setNumber(-stock.getStockNumber());
        stock.setCreateDate(new Date());
        Stock stock1 = baseMapper.selectOne(new QueryWrapper<Stock>().eq("item_id", stock.getItemId()));
        int number=stock1.getStockNumber()-stock.getStockNumber();
        stock1.setStockNumber(number);
        baseMapper.updateById(stock1);
        stockChangeService.save(stockChange);
        return true;
    }

    @Override
    public boolean updateStock(List<StockVo> stockVos) {

        List<Stock> stocks=new ArrayList<>();
        for (StockVo stockVo : stockVos) {
            Stock stock=new Stock();
            BeanUtils.copyProperties(stockVo,stock);
            stock.setCreateDate(new Date());
            stocks.add(stock);
        }

        this.updateBatchById(stocks);
        return false;
    }

    @Override
    public boolean updateStockNumber(Stock stock) {
        Stock stock1 = baseMapper.selectById(stock.getId());
        int number=stock.getStockNumber()-stock1.getStockNumber();
        stock.setCreateDate(new Date());
        baseMapper.updateById(stock);
        StockChange stockChange=new StockChange();
        stockChange.setNumber(number);
        stockChange.setItemId(stock.getItemId());
        stockChange.setCreatedDate(new Date());
        stockChangeService.save(stockChange);

        return true;
    }
}
