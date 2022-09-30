package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.StockItem;
import com.honghu.service.mapper.StockItemMapper;
import com.honghu.service.service.StockItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.vo.CoupleVo;
import com.honghu.service.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-20
 */
@Service
public class StockItemServiceImpl extends ServiceImpl<StockItemMapper, StockItem> implements StockItemService {
    @Autowired
    private StockItemMapper stockItemMapper;
    @Override
    public List<StockVo> getPage(long current, long limit) {
        List<StockItem> items = baseMapper.selectList(null);
        int count = 0;

        if (items != null && items.size() > 0) {
            count = items.size();
            int fromIndex = (int) ((current - 1) * limit);
            int toIndex = (int) (current * limit);
            if (toIndex > count) {
                toIndex = count;
            }
            List<StockVo> infos = stockItemMapper.getPage((long) fromIndex, (long) toIndex);

            return infos;
        }
        return null;
    }

    @Override
    public List<StockItem> getPageList(Page<StockItem> page) {
        List<StockItem> records = baseMapper.selectPage(page, null).getRecords();

        return records;
    }

    @Override
    public boolean add(StockItem stockItem) {
        boolean b = false;
        if (stockItem != null) {
            b = this.save(stockItem);
        }
        return b;

    }

    @Override
    public boolean update(StockItem stockItem) {
        int i = baseMapper.updateById(stockItem);
        return i>0?true:false;
    }

    @Override
    public StockItem getOneById(String id) {
        if (!StringUtils.isEmpty(id)) {
            StockItem info = baseMapper.selectById(id);
            return info;
        }

        return null;
    }
}
