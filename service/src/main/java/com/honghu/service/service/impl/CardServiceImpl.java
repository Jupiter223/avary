package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Card;
import com.honghu.service.mapper.CardMapper;
import com.honghu.service.service.CardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-09
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {


    @Override
    public List<Card> getPageListCard(Page<Card> page) {
        List<Card> records =baseMapper.selectPage(page,null).getRecords();

        return records ;
    }

    @Override
    public boolean add(Card card) {
        boolean b=false;
        if(card!=null){
            b = this.save(card);
        }
        return b;
    }

    @Override
    public Card getOneById(String id) {
        if (!StringUtils.isEmpty(id)){
            Card info = baseMapper.selectById(id);
            return info;
        }
        return null;
    }

    @Override
    public boolean update(Card card) {
        QueryWrapper<Card> wrapper=new QueryWrapper<>();
        wrapper.eq("id",card.getId());
        int i = baseMapper.update(card, wrapper);

        return i>0?true:false;
    }
}
