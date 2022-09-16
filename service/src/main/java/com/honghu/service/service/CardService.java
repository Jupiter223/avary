package com.honghu.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Card;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-09
 */
public interface CardService extends IService<Card> {

    List<Card> getPageListCard(Page<Card> page);

    boolean add(Card card);

    Card getOneById(String id);

    boolean update(Card card);
}
