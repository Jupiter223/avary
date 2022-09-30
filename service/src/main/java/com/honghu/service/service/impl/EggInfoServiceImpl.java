package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.HonghuException;
import com.honghu.service.common.ResultCode;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Calendar;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import com.honghu.service.mapper.EggInfoMapper;
import com.honghu.service.service.AvaryInfoService;
import com.honghu.service.service.CalendarService;
import com.honghu.service.service.EggInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.service.NestlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-10
 */
@Service
public class EggInfoServiceImpl extends ServiceImpl<EggInfoMapper, EggInfo> implements EggInfoService {
    @Autowired
    private EggInfoMapper eggInfoMapper;
    @Autowired
    private AvaryInfoService avaryInfoService;

    @Autowired
    private NestlingService nestlingService;

    @Autowired
    private CalendarService calendarService;

    @Override
    public List<EggInfo> getPageListEgg(Page<EggInfo> page) {
        List<EggInfo> records =baseMapper.selectPage(page,null).getRecords();

        return records ;
    }

    @Override
    public boolean recordHatch(EggInfo eggInfo) {
        if (!eggInfo.isFertilization()){
            eggInfo.setFertilization(true);
        }
        eggInfo.setHatch(true);
        eggInfo.setHatchDate(new Date());
        baseMapper.updateById(eggInfo);

        Nestling nestling = nestlingService.getOne(new QueryWrapper<Nestling>().eq("parent_id", eggInfo.getParentId()).orderByDesc("count").orderByDesc("nest"));
        Nestling nestling1=new Nestling();
        nestling1.setParentId(nestling.getParentId());
        nestling1.setNest(nestling.getNest());
        nestling1.setCount(nestling.getCount()+1);
        nestling1.setBirthday(new Date());
        nestlingService.add(nestling1);

        return true;
    }

    @Override
    public boolean add(EggInfo eggInfo) {
        boolean b=false;
        boolean b1=false;
        if(eggInfo!=null){
            b = this.save(eggInfo);
            Calendar calendar=new Calendar();
            calendar.setDate(eggInfo.getBirthday());
            calendar.setType("egg");
            calendar.setContent(eggInfo.getSpecies()+eggInfo.getParentLocation()+eggInfo.getParentNickname()+"第"+eggInfo.getNest()+"窝第"+eggInfo.getCount()+"颗蛋");
       b1 = calendarService.save(calendar);
        }
        return (b||b1);
    }

    @Override
    public EggInfo getOneById(String id) {
        if (!StringUtils.isEmpty(id)){
            EggInfo info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public boolean update(EggInfo eggInfo) {
        if (eggInfo.getHatchDate()!=null&&eggInfo.getBirthday()!=null){
            if (eggInfo.getHatchDate().before(eggInfo.getBirthday())||eggInfo.getHatchDate().equals(eggInfo.getBirthday())){
                throw new HonghuException(ResultCode.ERROR,"出壳日期必须在生蛋日期之后");
            }
        }

        QueryWrapper<EggInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("id",eggInfo.getId());
        int i = baseMapper.update(eggInfo, wrapper);

        return i>0?true:false;
    }

    @Override
    public List<EggInfo> getByParentId(String id) {
        if (!StringUtils.isEmpty(id)){
            QueryWrapper<EggInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("parent_id",id).orderByAsc("birthday").orderByAsc("nest").orderByAsc("count");
            List<EggInfo> infos = baseMapper.selectList(wrapper);
            return infos;
        }
        return null;
    }

    @Override
    public List<EggInfo> getThisYearByParentId(String id) {
        if (!StringUtils.isEmpty(id)){
            List<EggInfo> infos = eggInfoMapper.selectThisYearEggByParentId(id);
            return infos;
        }
        return null;
    }

    @Override
    public List<EggInfo> getByYearByParentId(String id, String year) {
        if (!StringUtils.isEmpty(id)){
            List<EggInfo> infos = eggInfoMapper.selectByYearByParentId(id,year);
            return infos;
        }
        return null;
    }

    @Override
    public EggInfo getCount(String id) {

        QueryWrapper<EggInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id).orderByDesc("nest").orderByDesc("count").last("limit 1");
        EggInfo eggInfo=new EggInfo();
        AvaryInfo avaryInfo = avaryInfoService.getOne(new QueryWrapper<AvaryInfo>().eq("couple_id", id));
        if (baseMapper.selectCount(wrapper)==0){
            eggInfo.setParentId(id);
            eggInfo.setNest(1);
            eggInfo.setCount(1);
            eggInfo.setBirthday(new Date());
            eggInfo.setSpecies(avaryInfo.getSpecies());
             }else{

        EggInfo info = baseMapper.selectOne(wrapper);

        eggInfo.setParentId(id);
        eggInfo.setNest(info.getNest());
        eggInfo.setCount(info.getCount()+1);
        eggInfo.setParentNickname(info.getParentNickname());
        eggInfo.setParentLocation(info.getParentLocation());
            eggInfo.setSpecies(avaryInfo.getSpecies());
            eggInfo.setBirthday(new Date());
        }

        return eggInfo;
    }






}
