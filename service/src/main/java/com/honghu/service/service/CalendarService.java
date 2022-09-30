package com.honghu.service.service;

import com.honghu.service.entity.Calendar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honghu.service.vo.CalendarVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-23
 */
public interface CalendarService extends IService<Calendar> {

    List<Calendar> getEvent(CalendarVo calendarVo);
}
