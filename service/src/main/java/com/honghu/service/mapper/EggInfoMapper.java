package com.honghu.service.mapper;

import com.honghu.service.entity.EggInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-10
 */
public interface EggInfoMapper extends BaseMapper<EggInfo> {
    Integer selectThisYearCount(String parentId);
    List<EggInfo> selectThisYearEggByParentId(String id);
    List<EggInfo> selectByYearByParentId(String id,String year);

}
