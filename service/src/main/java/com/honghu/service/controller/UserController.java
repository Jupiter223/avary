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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/service/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public R register(@RequestBody @Valid RegisterVo registerVo,BindingResult result){
        if(result.hasErrors()){
           return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg());
        }
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
            return R.error().message(BizCodeEnum.VAILD_EXCEPTION.getMsg());
        }
        String token;
        try {
            token = userService.login(loginVo);
        } catch (HonghuException e) {
            return R.error().message(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());
        }
        return R.ok().data("token", token);

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

