package com.honghu.service.mapper;

import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-12
 */
public interface NestlingMapper extends BaseMapper<Nestling> {
    Integer selectThisYearCount(String parentId);
    List<Nestling> selectThisYearEggByParentId(String id);
    List<Nestling> selectByYearByParentId(String id,String year);

    Boolean updateParentNickname(String nickname,String id);
    Boolean updateParentLocation(String location,String id);
}
