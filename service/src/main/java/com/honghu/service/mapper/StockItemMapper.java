package com.honghu.service.mapper;

import com.honghu.service.entity.StockItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honghu.service.vo.StockVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-20
 */
public interface StockItemMapper extends BaseMapper<StockItem> {

    List<StockVo> getPage(Long fromIndex, Long toIndex);

    List<StockItem> searchByCodition(StockVo stockVo);

}
