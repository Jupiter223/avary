package com.honghu.service.controller;


import com.honghu.service.common.HonghuException;
import com.honghu.service.common.R;
import com.honghu.service.entity.User;
import com.honghu.service.exception.BizCodeEnum;
import com.honghu.service.exception.UserExistException;
import com.honghu.service.service.UserService;
import com.honghu.service.utils.MD5;
import com.honghu.service.vo.RegisterVo;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.apache.ibatis.annotations.Param;
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
 * @since 2022-09-01
 */
@RestController
@CrossOrigin
@RequestMapping("/service/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public R register(@RequestBody @Valid RegisterVo registerVo,BindingResult result){
        if (result.hasErrors()) {
            List<String> errors=new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg()).data("data",errors);}
        try {
            userService.register(registerVo);
        } catch (UserExistException e) {
           return R.error().message(BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }
            return R.ok();

    }
    @PostMapping("login")
    public R login(@Valid @RequestBody RegisterVo loginVo,BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors=new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg()).data("data",errors);}
        String token;
        try {
            token = userService.login(loginVo);
        } catch (HonghuException e) {
            return R.error().message(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());
        }
        return R.ok().data("token", token);
    }

    @GetMapping("getinfo")
    public R getInfo(@Param("token")String token){
User user=userService.getByToken(token);
return R.ok().data("data",user);
    }

    @PostMapping("updatepassword")
    public R update(@Valid @RequestBody RegisterVo updatevo,BindingResult result){
        if (result.hasErrors()) {
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg());
        }
         int i=userService.updatePasswordByUser(updatevo);
        if (i==0){
            return R.error().message(BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
        }
        return R.ok();
    }
}

