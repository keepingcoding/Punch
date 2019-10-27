package com.example.punch.controller;

import com.example.punch.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzs
 * @date 2019/10/27 18:22
 */
@Slf4j
@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody UserDTO userDTO){

    }
}
