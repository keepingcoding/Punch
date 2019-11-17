package com.example.punch.service.impl;

import com.example.punch.dao.ext.UserExtMapper;
import com.example.punch.model.User;
import com.example.punch.service.LoginService;
import com.example.punch.util.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzs
 * @date 2019/11/13 21:29
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public User checkUser(User user) {
        String loginName = user.getLoginName();
        String encryptPasswd = EncryptUtils.encryptMd5(user.getPassword());

        return this.userExtMapper.checkUser(loginName, encryptPasswd);
    }
}
