package com.honghu.service.controller;


import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Stock;
import com.honghu.service.service.StockChangeService;
import com.honghu.service.service.StockService;
import com.honghu.service.vo.StockVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-21
 */
@RestController
@CrossOrigin
@RequestMapping("/service/stock")
public class StockController {
    @Autowired
    private StockChangeService stockChangeService;
    @Autowired
    private StockService stockService;

    @PostMapping("/update")
    public R update(@RequestBody Stock stock){
        stock.setCreateDate(new Date());
        boolean update = stockService.updateById(stock);
        return update?R.ok():R.error();
    }
    @PostMapping("/add")
    public R add(@RequestBody Stock stock) {



        boolean result = stockService.add(stock);
        return result ? R.ok() : R.error();

    }

    @PostMapping("/reduce")
    public R reduce(@RequestBody Stock stock) {

        boolean result = stockService.reduce(stock);
        return result ? R.ok() : R.error();

    }
    @ApiOperation(value = "删除库存资料")
    @DeleteMapping("{id}")
    public R remove(@ApiParam(name = "id", value = "库存ID", required = true)
                        @PathVariable String id) {
        boolean flag = stockService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/updateStock")
    public R update(@RequestBody List<StockVo> stockVos){

        boolean update = stockService.updateStock(stockVos);
        return update?R.ok():R.error();
    }

    @PostMapping("/updateStockNumber")
    public R updateStockNumber(@RequestBody Stock stock){

        boolean update = stockService.updateStockNumber(stock);
        return update?R.ok():R.error();
    }


}

