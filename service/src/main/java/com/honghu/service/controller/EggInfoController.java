package com.honghu.service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.HonghuException;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.service.EggInfoService;
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
 * @since 2022-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/service/egg-info")
public class EggInfoController {

    @Autowired
    private EggInfoService eggInfoService;
    @GetMapping("{current}/{limit}")
    public R pageListEgg(@PathVariable long current, @PathVariable long limit) {
        Page<EggInfo> page = new Page<>(current, limit);
        List<EggInfo> records = eggInfoService.getPageListEgg(page);
        int total = eggInfoService.count(null);
        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/add")
    public R add(@RequestBody EggInfo eggInfo) {
        boolean result = eggInfoService.add(eggInfo);
        return result ? R.ok() : R.error();

    }

    @ApiOperation(value = "删除蛋资料")
    @DeleteMapping("{id}")
    public R removeCard(@ApiParam(name = "id", value = "卡ID", required = true)
                        @PathVariable String id) {
        boolean flag = eggInfoService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/getbyid/{id}")
    public R getById (@PathVariable String id){
        EggInfo info=eggInfoService.getOneById(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}

    @GetMapping("/getbyparentid/{id}")
    public R getByParentId (@PathVariable String id){
        List<EggInfo> info=eggInfoService.getByParentId(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}



    @GetMapping("/getnowbyparentid/{id}")
    public R getThisYearByParentId (@PathVariable String id){
        List<EggInfo> info=eggInfoService.getThisYearByParentId(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}


    @GetMapping("/getbyparentid/id={id}/year={year}")
    public R getByYearByParentId (@PathVariable String id,@PathVariable String year){
        List<EggInfo> info=eggInfoService.getByYearByParentId(id,year);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}

    @PostMapping("/update")
    public R update(@RequestBody EggInfo eggInfo){
        boolean update ;
        try {
            update = eggInfoService.update(eggInfo);
        } catch (HonghuException e) {
            System.out.println(e.getMsg());
            return R.error().message(e.getMsg());
        }
        return update?R.ok():R.error();
    }

}

