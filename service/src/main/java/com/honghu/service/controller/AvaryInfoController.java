package com.honghu.service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.mapper.AvaryInfoMapper;
import com.honghu.service.service.AvaryInfoService;
import com.honghu.service.vo.AvaryVo;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-05
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/service/avary-info")
public class AvaryInfoController {

    @Autowired
    private AvaryInfoService avaryInfoService;

    @Autowired
    private AvaryInfoMapper avaryInfoMapper;

    @GetMapping("/getall")
    public R getAll() {
        List<AvaryInfo> avaryInfos = avaryInfoService.list(null);
        return R.ok().data("data", avaryInfos);
    }

    @GetMapping("/getbyid/{id}")
    public R getById (@PathVariable String id){
    AvaryInfo info=avaryInfoService.getOneById(id);
    if (info!=null){
    return R.ok().data("info",info);}
    else return R.error().message("id有误");}


    @GetMapping("/getbycoupleid/{id}")
    public R getByCoupeId (@PathVariable String id){
        List<AvaryInfo> info=avaryInfoService.getOneByCoupleId(id);
        if (info!=null){
            return R.ok().data("info",info);}
        else return R.error().message("id有误");}


    @GetMapping("{current}/{limit}")
    public R pageListAvary(@PathVariable long current, @PathVariable long limit) {
        Page<AvaryInfo> page = new Page<>(current, limit);
        List<AvaryInfo> records = avaryInfoService.getPageListAvary(page);
        int total = avaryInfoService.count(null);



        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("/add")
    public R add(@RequestBody AvaryInfo avaryInfo) {
        boolean result = avaryInfoService.add(avaryInfo);
        return result ? R.ok() : R.error();

    }

    @PostMapping("/update")
    public R update(@RequestBody AvaryInfo avaryInfo){
        boolean update = avaryInfoService.update(avaryInfo);
        return update?R.ok():R.error();
    }

    @ApiOperation(value = "删除鸟资料")
    @DeleteMapping("{id}")
    public R removeInfo(@ApiParam(name = "id", value = "鸟资料ID", required = true)
                           @PathVariable String id) {
        boolean flag = avaryInfoService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/searchByCondition")
    public R searchByCondition(@RequestBody AvaryVo avaryVo){

        if (avaryVo!=null&& !ObjectUtils.isEmpty(avaryVo)){
            List<AvaryInfo> info = avaryInfoMapper.searchByCondition(avaryVo);
            return R.ok().data("info",info);
        }


        return R.error().message("请输入检索条件");
    }

    @PostMapping("/getCouple")
    public R getCoupleByCondition(@RequestBody AvaryVo avaryVo){

        if (avaryVo!=null&& !ObjectUtils.isEmpty(avaryVo)){
            String coupleId = avaryInfoMapper.searchCoupleByCondition(avaryVo);
            if (coupleId==null|| coupleId.equals("")){
                return R.error().message("未查到该种对");
            }
            return R.ok().data("coupleId",coupleId);
        }


        return R.error().message("请输入检索条件");
    }

}

