package com.honghu.service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.StockItem;
import com.honghu.service.mapper.StockItemMapper;
import com.honghu.service.service.StockItemService;
import com.honghu.service.vo.StockVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-20
 */
@RestController
@CrossOrigin
@RequestMapping("/service/stock-item")
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    @Autowired
    private StockItemMapper stockItemMapper;

    @GetMapping("/{current}/{limit}")
    public R getPage(@PathVariable long current, @PathVariable long limit){
       List<StockVo> infos= stockItemService.getPage(current,limit);
return R.ok().data("infos",infos);
    }

    @GetMapping("/item/{current}/{limit}")
    public R pageList(@PathVariable long current, @PathVariable long limit) {
        Page<StockItem> page = new Page<>(current, limit);
        List<StockItem> records = stockItemService.getPageList(page);
        int total = stockItemService.count(null);



        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/add")
    public R add(@RequestBody StockItem stockItem) {
        boolean result = stockItemService.add(stockItem);
        return result ? R.ok() : R.error();

    }

    @PostMapping("/update")
    public R update(@RequestBody StockItem stockItem){
        boolean update = stockItemService.update(stockItem);
        return update?R.ok():R.error();
    }

    @ApiOperation(value = "删除粮食资料")
    @DeleteMapping("{id}")
    public R removeInfo(@ApiParam(name = "id", value = "粮食ID", required = true)
                        @PathVariable String id) {
        boolean flag = stockItemService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/getbyid/{id}")
    public R getById (@PathVariable String id){
        StockItem info=stockItemService.getOneById(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}

    @PostMapping("/searchByCondition")
    public R searchByCondition (@RequestBody StockVo stockVo){
        List<StockItem> infos = stockItemMapper.searchByCodition(stockVo);
        if (infos!=null){
            return R.ok().data("info",infos);}
        else return R.error().message("id有误");}

}

