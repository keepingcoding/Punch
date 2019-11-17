package com.example.punch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制页面跳转
 *
 * @author zzs
 * @date 2019/10/27 17:57
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "/page/main.html";
    }

}
