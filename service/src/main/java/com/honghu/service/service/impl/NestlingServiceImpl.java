package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Calendar;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import com.honghu.service.mapper.NestlingMapper;
import com.honghu.service.service.CalendarService;
import com.honghu.service.service.NestlingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-12
 */
@Service
public class NestlingServiceImpl extends ServiceImpl<NestlingMapper, Nestling> implements NestlingService {
    @Autowired
    private NestlingMapper nestlingMapper;

    @Autowired
    private CalendarService calendarService;

    @Override
    public List<Nestling> getPageListEgg(Page<Nestling> page) {
        List<Nestling> records =baseMapper.selectPage(page,null).getRecords();

        return records ;
    }

    @Override
    public boolean add(Nestling nestling) {
        boolean b=false;
        boolean b1=false;
        if(nestling!=null){
            b = this.save(nestling);
            Calendar calendar=new Calendar();
            calendar.setDate(nestling.getBirthday());
            calendar.setType("nestling");
            calendar.setContent(nestling.getSpeices()+nestling.getParentLocation()+nestling.getParentNickname()+"第"+nestling.getNest()+"窝第"+nestling.getCount()+"个小鸟出壳");
            b1 = calendarService.save(calendar);
        }
        return b||b1;
    }

    @Override
    public Nestling getOneById(String id) {
        if (!StringUtils.isEmpty(id)){
            Nestling info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public boolean update(Nestling nestling) {
        QueryWrapper<Nestling> wrapper=new QueryWrapper<>();
        wrapper.eq("id",nestling.getId());
        int i = baseMapper.update(nestling, wrapper);

        return i>0?true:false;
    }

    @Override
    public List<Nestling> getByParentId(String id) {
        if (!StringUtils.isEmpty(id)){
            QueryWrapper<Nestling> wrapper=new QueryWrapper<>();
            wrapper.eq("parent_id",id).orderByAsc("birthday").orderByAsc("nest").orderByAsc("count");
            List<Nestling> infos = baseMapper.selectList(wrapper);
            return infos;
        }
        return null;
    }

    @Override
    public List<Nestling> getThisYearByParentId(String id) {
        if (!StringUtils.isEmpty(id)){
            List<Nestling> infos = nestlingMapper.selectThisYearEggByParentId(id);
            return infos;
        }
        return null;
    }

    @Override
    public List<Nestling> getByYearByParentId(String id, String year) {
        if (!StringUtils.isEmpty(id)){
            List<Nestling> infos = nestlingMapper.selectByYearByParentId(id,year);
            return infos;
        }
        return null;
    }

    @Override
    public boolean updateDeath(List<Nestling> nestlings) {
        boolean update=false;
        for (Nestling nestling : nestlings) {
            nestling.setIsDead(1);
            update = this.update(nestling);
        }

//TODO
        //返回值
        return update;
    }
}
