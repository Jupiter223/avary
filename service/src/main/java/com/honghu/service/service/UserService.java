package com.honghu.service.service;

import com.honghu.service.common.R;
import com.honghu.service.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honghu.service.vo.RegisterVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jupiter
 * @since 2022-09-01
 */
public interface UserService extends IService<User> {

    void register(RegisterVo registerVo);

    String login(RegisterVo loginVo);

    Integer updatePasswordByUser(RegisterVo updatevo);
}
