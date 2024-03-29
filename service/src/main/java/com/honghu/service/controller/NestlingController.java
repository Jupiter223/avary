package com.honghu.service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.HonghuException;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.EggInfo;
import com.honghu.service.entity.Nestling;
import com.honghu.service.exception.BizCodeEnum;
import com.honghu.service.service.EggInfoService;
import com.honghu.service.service.NestlingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-12
 */
@RestController
@CrossOrigin
@RequestMapping("/service/nestling")
public class NestlingController {

    @Autowired
    private NestlingService nestlingService;
    @GetMapping("{current}/{limit}")
    public R pageListEgg(@PathVariable long current, @PathVariable long limit) {
        Page<Nestling> page = new Page<>(current, limit);
        List<Nestling> records = nestlingService.getPageListEgg(page);
        int total = nestlingService.count(null);
        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/add")
    public R add(@RequestBody @Valid Nestling nestling, BindingResult result) {
        boolean add;
        if (result.hasErrors()) {
            List<String> errors=new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg()).data("data",errors);
        }try{
            add= nestlingService.add(nestling);
        }catch (HonghuException e) {
            return R.error().message(e.getMsg());
        }
        return add ? R.ok() : R.error();

    }

    @ApiOperation(value = "删除雏鸟资料")
    @DeleteMapping("{id}")
    public R removeCard(@ApiParam(name = "id", value = "雏鸟ID", required = true)
                        @PathVariable String id) {
        boolean flag = nestlingService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    @GetMapping("/getbyid/{id}")
    public R getById (@PathVariable String id){
        Nestling info=nestlingService.getOneById(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}

    @GetMapping("/getbyparentid/{id}")
    public R getByParentId (@PathVariable String id){
        List<Nestling> info=nestlingService.getByParentId(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}



    @GetMapping("/getnowbyparentid/{id}")
    public R getThisYearByParentId (@PathVariable String id){
        List<Nestling> info=nestlingService.getThisYearByParentId(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}


    @GetMapping("/getbyparentid/id={id}/year={year}")
    public R getByYearByParentId (@PathVariable String id,@PathVariable String year){
        List<Nestling> info=nestlingService.getByYearByParentId(id,year);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}


    @PostMapping("/update")
    public R update(@RequestBody @Valid Nestling nestling,BindingResult result){

        boolean update;
        if (result.hasErrors()) {
            List<String> errors=new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg()).data("data",errors);
        }try{
            update= nestlingService.update(nestling);
        }catch (HonghuException e) {
            return R.error().message(e.getMsg());
        }
        return update ? R.ok() : R.error();

    }

    @PostMapping("/updateDeath")
    public R updateDeath(@RequestBody List<Nestling> nestlings){

      boolean update=nestlingService.updateDeath(nestlings);
        return update ? R.ok() : R.error();

    }



}

