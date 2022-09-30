package com.honghu.service.service.impl;

import com.honghu.service.entity.Calendar;
import com.honghu.service.mapper.CalendarMapper;
import com.honghu.service.service.CalendarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.vo.CalendarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-23
 */
@Service
public class CalendarServiceImpl extends ServiceImpl<CalendarMapper, Calendar> implements CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Override
    public List<Calendar> getEvent(CalendarVo calendarVo) {
        List<Calendar> events = calendarMapper.getEvent(calendarVo);

        return events;
    }
}
