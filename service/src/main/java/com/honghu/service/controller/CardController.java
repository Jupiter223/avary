package com.honghu.service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Card;
import com.honghu.service.mapper.CardMapper;
import com.honghu.service.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-09
 */
@RestController
@CrossOrigin
@RequestMapping("/service/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CardMapper cardMapper;

    @GetMapping("{current}/{limit}")
    public R pageListCard(@PathVariable long current, @PathVariable long limit) {
        Page<Card> page = new Page<>(current, limit);
        List<Card> records = cardService.getPageListCard(page);
        int total = cardService.count(null);



        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/add")
    public R add(@RequestBody Card card) {
        boolean result = cardService.add(card);
        return result ? R.ok() : R.error();

    }


    @ApiOperation(value = "删除卡资料")
    @DeleteMapping("{id}")
    public R removeCard(@ApiParam(name = "id", value = "卡ID", required = true)
                        @PathVariable String id) {
        boolean flag = cardService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/getbyid/{id}")
    public R getById (@PathVariable String id){
        Card info=cardService.getOneById(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}

    @PostMapping("/update")
    public R update(@RequestBody Card card){
        boolean update = cardService.update(card);
        return update?R.ok():R.error();
    }

    @GetMapping("/searchCard/{ring}")
    public R searchCard(@PathVariable String ring){

        List<String> cards = cardMapper.searchCard(ring);
        if (cards==null){
            return R.error().message("未找到相关卡片");
        }
        return R.ok().data("cards",cards);
    }



}

