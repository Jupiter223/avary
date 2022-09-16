package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.*;
import com.honghu.service.mapper.CoupleInfoMapper;
import com.honghu.service.mapper.EggInfoMapper;
import com.honghu.service.mapper.NestlingMapper;
import com.honghu.service.service.AvaryInfoService;
import com.honghu.service.service.CoupleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.service.EggInfoService;
import com.honghu.service.service.NestlingService;
import com.honghu.service.vo.CoupleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-10
 */
@Service
public class CoupleInfoServiceImpl extends ServiceImpl<CoupleInfoMapper, CoupleInfo> implements CoupleInfoService {
    @Autowired
    private AvaryInfoService avaryInfoService;
    @Autowired
    private EggInfoService eggInfoService;
    @Autowired
    private EggInfoMapper eggInfoMapper;
    @Autowired
    private NestlingService nestlingService;

    @Autowired
    private NestlingMapper nestlingMapper;


    @Override
    public boolean addList(List<AvaryInfo> infos) {
        CoupleInfo coupleInfo = new CoupleInfo();
        for (AvaryInfo info : infos) {
            if (info.getGender().equals("male")) {
                coupleInfo.setMaleId(info.getId());
            } else if (info.getGender().equals("female")) {
                coupleInfo.setFemaleId(info.getId());
            }

        }
        int i = baseMapper.insert(coupleInfo);

        for (AvaryInfo info : infos) {
            info.setCoupleId(coupleInfo.getId());
            avaryInfoService.update(info);
        }

        return i > 0 ? true : false;
    }

//    @Override
//    public List<CoupleVo> getPageListCouple(Page<CoupleVo> page) {
////        List<CoupleVo> records =baseMapper.selectPage(page,null).getRecords();
//baseMapper.
//        return records ;
//    }

    @Override
    public boolean add(CoupleInfo coupleInfo) {
        boolean b = false;
        if (coupleInfo != null) {

            b = this.save(coupleInfo);
        }
        return b;
    }

    @Override
    public CoupleInfo getOneById(String id) {
        if (!StringUtils.isEmpty(id)) {
            CoupleInfo info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public List<CoupleVo> getAll() {
        List<CoupleInfo> infos = baseMapper.selectList(null);
        List<CoupleVo> result = new ArrayList<>();
        for (CoupleInfo info : infos) {
            CoupleVo coupleVo = new CoupleVo();
            coupleVo.setId(info.getId());
            coupleVo.setOtherInfo(info.getOtherInfo());
            AvaryInfo female = avaryInfoService.getOneById(info.getFemaleId());
            AvaryInfo male = avaryInfoService.getOneById(info.getMaleId());
            List<AvaryInfo> parentList = new ArrayList<>();
            parentList.add(male);
            parentList.add(female);

            coupleVo.setParentList(parentList);
            int eggCount = eggInfoService.count(new QueryWrapper<EggInfo>().eq("parent_id", info.getId()));
            Integer eggThisYearCount = eggInfoMapper.selectThisYearCount(info.getId());
            Integer nestlingThisYearCount = nestlingMapper.selectThisYearCount(info.getId());
            int nestlingCount = nestlingService.count(new QueryWrapper<Nestling>().eq("parent_id", info.getId()));
            List<AvaryInfo> avaryInfos = avaryInfoService.list(new QueryWrapper<AvaryInfo>().eq("parent_id", info.getId()));
            coupleVo.setEggTotal(eggCount);
            coupleVo.setEggThisYear(eggThisYearCount);
            coupleVo.setNestlingThisYear(nestlingThisYearCount);
            coupleVo.setNestlingTotal(nestlingCount);
            coupleVo.setList(avaryInfos);
            result.add(coupleVo);
        }

        return result;
    }

}
