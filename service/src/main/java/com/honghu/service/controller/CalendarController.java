package com.honghu.service.controller;


import com.honghu.service.common.R;
import com.honghu.service.entity.Calendar;
import com.honghu.service.service.CalendarService;
import com.honghu.service.vo.CalendarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-23
 */
@RestController
@CrossOrigin
@RequestMapping("/service/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping("getEvent")
    public R getIvent(@RequestBody CalendarVo calendarVo){
        List<Calendar> events=calendarService.getEvent(calendarVo);
      return R.ok().data("events",events);
    }

    @PostMapping("addEvent")
    public R addEvent(@RequestBody Calendar calendar){
        boolean save = calendarService.save(calendar);
        return save?R.ok().data("date",calendar.getDate()):R.error();
    }

    @PostMapping("updateEvent")
    public R updateEvent(@RequestBody Calendar calendar){
        boolean update = calendarService.updateById(calendar);
        return update?R.ok().data("date",calendar.getDate()):R.error();
    }

    @DeleteMapping("/remove/{id}")
    public R removeEvent(@PathVariable String id){
        boolean b = calendarService.removeById(id);
        return b?R.ok():R.error();
    }

}

