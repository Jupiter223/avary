package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import com.honghu.service.mapper.AvaryInfoMapper;
import com.honghu.service.mapper.EggInfoMapper;
import com.honghu.service.mapper.NestlingMapper;
import com.honghu.service.service.AvaryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.honghu.service.service.EggInfoService;
import com.honghu.service.service.NestlingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
@Service
public class AvaryInfoServiceImpl extends ServiceImpl<AvaryInfoMapper, AvaryInfo> implements AvaryInfoService {
    @Autowired
    private EggInfoService eggInfoService;

    @Autowired
    private EggInfoMapper eggInfoMapper;

    @Autowired
    private NestlingService nestlingService;

    @Autowired
    private NestlingMapper nestlingMapper;

    @Override
    public boolean add(AvaryInfo avaryInfo) {
        boolean b = false;
        if (avaryInfo != null) {
            b = this.save(avaryInfo);
        }
        return b;
    }

    @Override
    public List<AvaryInfo> getPageListAvary(Page<AvaryInfo> page) {
//        IPage<AvaryInfo> page1 = this.page(page, null);
//        List<AvaryInfo> records=page1.getRecords();
//

        List<AvaryInfo> records = baseMapper.selectPage(page, null).getRecords();

        return records;
    }

    @Override
    public AvaryInfo getOneById(String id) {
        if (!StringUtils.isEmpty(id)) {
            AvaryInfo info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public boolean update(AvaryInfo avaryInfo) {
        if (avaryInfo.isDeath()){
            avaryInfo.setDeadDate(new Date());
        }
        if (avaryInfo.isOutStatus()){
            avaryInfo.setOutDate(new Date());
        }
        //TODO
        // 事务
        if (avaryInfo.getCoupleId() != null && avaryInfo.getCoupleId() != "") {

            //更新蛋资料库中的昵称//更新雏鸟库中的昵称
            int count1 = eggInfoService.count(new QueryWrapper<EggInfo>().eq("parent_id", avaryInfo.getCoupleId()));
            if (count1 > 0) {
                if (avaryInfo.getNickname() != null && avaryInfo.getNickname() != "") {
                    eggInfoMapper.updateParentNickname(avaryInfo.getNickname(), avaryInfo.getCoupleId());
                }
                if (avaryInfo.getLocation() != null && avaryInfo.getLocation() != "") {
                    eggInfoMapper.updateParentLocation(avaryInfo.getLocation(), avaryInfo.getCoupleId());
                }
            }
            int count2 = nestlingService.count(new QueryWrapper<Nestling>().eq("parent_id", avaryInfo.getCoupleId()));
            if (count2 > 0) {
                if (avaryInfo.getNickname() != null && avaryInfo.getNickname() != "") {
                    nestlingMapper.updateParentNickname(avaryInfo.getNickname(), avaryInfo.getCoupleId());
                }
                if (avaryInfo.getLocation() != null && avaryInfo.getLocation() != "") {
                    nestlingMapper.updateParentLocation(avaryInfo.getLocation(), avaryInfo.getCoupleId());
                }
            }
            if (avaryInfo.getGender().equals("male")) {
                AvaryInfo avaryInfo1 = new AvaryInfo();
                avaryInfo1.setLocation(avaryInfo.getLocation());
                baseMapper.update(avaryInfo1, new QueryWrapper<AvaryInfo>().eq("couple_id", avaryInfo.getCoupleId()).eq("gender", "female"));
            }
            if (avaryInfo.getGender().equals("female")) {
                AvaryInfo avaryInfo1 = new AvaryInfo();
                avaryInfo1.setLocation(avaryInfo.getLocation());
                baseMapper.update(avaryInfo1, new QueryWrapper<AvaryInfo>().eq("couple_id", avaryInfo.getCoupleId()).eq("gender", "male"));
            }
        }

        QueryWrapper<AvaryInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", avaryInfo.getId());
        int i = baseMapper.update(avaryInfo, wrapper);

        return i > 0 ? true : false;
    }

    @Override
    public List<AvaryInfo> getOneByCoupleId(String id) {
        if (!StringUtils.isEmpty(id)) {
            QueryWrapper<AvaryInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("couple_id", id);
            List<AvaryInfo> infos = baseMapper.selectList(wrapper);
            return infos;
        }
        return null;
    }


}
