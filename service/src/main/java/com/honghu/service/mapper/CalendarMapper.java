package com.honghu.service.mapper;

import com.honghu.service.entity.Calendar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honghu.service.vo.CalendarVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-23
 */
public interface CalendarMapper extends BaseMapper<Calendar> {
    List<Calendar> getEvent(CalendarVo calendarVo);

}
