package com.honghu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honghu.service.common.HonghuException;
import com.honghu.service.common.R;
import com.honghu.service.entity.User;
import com.honghu.service.exception.BizCodeEnum;
import com.honghu.service.exception.UserExistException;
import com.honghu.service.mapper.UserMapper;
import com.honghu.service.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honghu.service.utils.JwtUtils;
import com.honghu.service.utils.MD5;
import com.honghu.service.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(RegisterVo registerVo) {
       checkUserNameUnique(registerVo.getUsername());
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        password = MD5.encrypt(password);
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        this.save(user);


    }

    private void checkUserNameUnique(String username) {
        Integer count = baseMapper.selectCount(new QueryWrapper<User>().eq("username", username));
        if (count>0){
            throw new UserExistException();
        }
    }

    @Override
    public String login(RegisterVo loginVo) {

        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", loginVo.getUsername()));
        if (user==null){
            throw new HonghuException(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());
        }
        if (!MD5.encrypt(loginVo.getPassword()).equals(user.getPassword())) {
            throw new HonghuException(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(), BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());

        }

        String token = JwtUtils.getJwtToken(String.valueOf(user.getId()), user.getUsername());
        user.setToken(token);
        baseMapper.updateById(user);

        return token;
    }

    @Override
    public Integer updatePasswordByUser(RegisterVo updatevo) {
        String password = MD5.encrypt(updatevo.getPassword());
        String username = updatevo.getUsername();

        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username",username));

        user.setPassword(password);

        int i = baseMapper.updateById(user);

        return i;
    }

    @Override
    public User getByToken(String token) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("token", token));
        return user;
    }
}
