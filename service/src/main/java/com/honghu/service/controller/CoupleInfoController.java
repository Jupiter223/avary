package com.honghu.service.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honghu.service.common.R;
import com.honghu.service.entity.AvaryInfo;
import com.honghu.service.entity.Card;
import com.honghu.service.entity.CoupleInfo;
import com.honghu.service.service.AvaryInfoService;
import com.honghu.service.service.CoupleInfoService;
import com.honghu.service.vo.CoupleVo;
import com.sun.jersey.core.spi.factory.InjectableProviderFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/service/couple-info")
public class CoupleInfoController {
    @Autowired
    private CoupleInfoService coupleInfoService;

    @Autowired
    private AvaryInfoService avaryInfoService;

    @PostMapping("/add-from-list")
    public R addFromList(@RequestBody List<AvaryInfo> infos) {
        for (AvaryInfo info : infos) {
            if (StringUtils.isEmpty(info.getGender())){
                return R.error().message("性别不能为空");
            }
        }

        boolean result = coupleInfoService.addList(infos);
        return result ? R.ok() : R.error();

    }

    @PostMapping("/add")
    public R add(@RequestBody CoupleInfo coupleInfo) {

        QueryWrapper<CoupleInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("maleId",coupleInfo.getMaleId());
        QueryWrapper<CoupleInfo> wrapper1=new QueryWrapper<>();
        wrapper1.eq("femaleId",coupleInfo.getFemaleId());
        if (coupleInfoService.count(wrapper)>0&&coupleInfoService.count(wrapper1)>0){
           R.error().message("该种对已存在");
        }
        boolean result = coupleInfoService.add(coupleInfo);
        return result ? R.ok() : R.error();

    }

    @GetMapping("{current}/{limit}")
    public R pageListCouple(@PathVariable long current, @PathVariable long limit) {
        int count = 0;
        List<CoupleVo> coupleVos = coupleInfoService.getAll();
        List<CoupleVo> pageList=new ArrayList<>();
        if (coupleVos != null && coupleVos.size() > 0) {
            count = coupleVos.size();
            int fromIndex = (int) ((current-1) * limit);
            int toIndex = (int) (current * limit);
            if (toIndex > count) {
                toIndex = count;
            }
            pageList = coupleVos.subList(fromIndex, toIndex);


        }
        int total = coupleVos.size();
        return R.ok().data("total", total).data("rows", pageList);
    }

        @GetMapping("/getall")
        public R getAll () {
            List<CoupleVo> records = coupleInfoService.getAll();
            return R.ok().data("records", records);
        }


        @GetMapping("/getbyid/{id}")
        public R getById (@PathVariable String id){
            CoupleInfo info = coupleInfoService.getOneById(id);
            if (info != null) {
                return R.ok().data("info", info);
            } else return R.error().message("id有误");
        }

        @ApiOperation(value = "删除配对资料")
        @DeleteMapping("{id}")
        public R removeCard (@ApiParam(name = "id", value = "卡ID", required = true)
        @PathVariable String id){
            boolean flag = coupleInfoService.removeById(id);
            if (flag) {
                return R.ok();
            } else {
                return R.error();
            }
        }

        @PostMapping("/updateOtherInfo")
        public R updateOtherInfo(@RequestBody CoupleInfo coupleInfo){
            boolean b = coupleInfoService.updateById(coupleInfo);
            return b?R.ok():R.error();
        }


}

