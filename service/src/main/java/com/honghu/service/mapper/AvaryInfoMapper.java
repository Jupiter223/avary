package com.honghu.service.mapper;

import com.honghu.service.entity.AvaryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honghu.service.vo.AvaryVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
public interface AvaryInfoMapper extends BaseMapper<AvaryInfo> {
    List<AvaryInfo> searchByCondition(AvaryVo avaryVo);

    String searchCoupleByCondition(AvaryVo avaryVo);

}
