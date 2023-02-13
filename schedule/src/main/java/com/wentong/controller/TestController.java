package com.wentong.controller;

import com.wentong.saver.Saver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Saver saver;

    @GetMapping("/url")
    public String getUrl() {
        saver.save("http://www.baidu.com");
        return "ok";
    }

}
