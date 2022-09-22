package com.honghu.service.mapper;

import com.honghu.service.entity.Card;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-09
 */
public interface CardMapper extends BaseMapper<Card> {
    List<String> searchCard(String ring);

}
