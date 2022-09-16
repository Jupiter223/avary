package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.mapper.AvaryInfoMapper;
import com.honghu.service.service.AvaryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
@Service
public class AvaryInfoServiceImpl extends ServiceImpl<AvaryInfoMapper, AvaryInfo> implements AvaryInfoService {

    @Override
    public boolean add(AvaryInfo avaryInfo) {
        boolean b=false;
        if(avaryInfo!=null){
            b = this.save(avaryInfo);



        }
        return b;
    }

    @Override
    public List<AvaryInfo> getPageListAvary(Page<AvaryInfo> page) {
//        IPage<AvaryInfo> page1 = this.page(page, null);
//        List<AvaryInfo> records=page1.getRecords();
//

        List<AvaryInfo> records =baseMapper.selectPage(page,null).getRecords();

        return records ;
    }

    @Override
    public AvaryInfo getOneById(String id) {
        if (!StringUtils.isEmpty(id)){
            AvaryInfo info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public boolean update(AvaryInfo avaryInfo) {
        QueryWrapper <AvaryInfo>wrapper=new QueryWrapper<>();
        wrapper.eq("id",avaryInfo.getId());
        int i = baseMapper.update(avaryInfo, wrapper);

        return i>0?true:false;
    }

    @Override
    public List<AvaryInfo> getOneByCoupleId(String id) {
        if (!StringUtils.isEmpty(id)){
            QueryWrapper<AvaryInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("couple_id",id);
            List<AvaryInfo> infos = baseMapper.selectList(wrapper);
            return infos;
        }
        return null;
    }


}
