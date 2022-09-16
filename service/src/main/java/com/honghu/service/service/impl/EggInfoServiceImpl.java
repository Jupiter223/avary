package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.HonghuException;
import com.honghu.service.common.ResultCode;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.mapper.EggInfoMapper;
import com.honghu.service.service.EggInfoService;
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
 * @since 2022-09-10
 */
@Service
public class EggInfoServiceImpl extends ServiceImpl<EggInfoMapper, EggInfo> implements EggInfoService {
    @Autowired
    private EggInfoMapper eggInfoMapper;

    @Override
    public List<EggInfo> getPageListEgg(Page<EggInfo> page) {
        List<EggInfo> records =baseMapper.selectPage(page,null).getRecords();

        return records ;
    }

    @Override
    public boolean add(EggInfo eggInfo) {
        boolean b=false;
        if(eggInfo!=null){
            b = this.save(eggInfo);
        }
        return b;
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
        if (eggInfo.getHatchDate().before(eggInfo.getBirthday())||eggInfo.getHatchDate().equals(eggInfo.getBirthday())){
            throw new HonghuException(ResultCode.ERROR,"出壳日期必须在生蛋日期之后");
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
            wrapper.eq("parent_id",id).orderByDesc("birthday");
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
}
