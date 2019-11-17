package com.example.punch.service;

import com.example.punch.model.User;

/**
 * @author zzs
 * @date 2019/11/13 21:29
 */
public interface LoginService {
    /** 用户登录 **/
    User checkUser(User user);
}
