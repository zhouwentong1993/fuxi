package com.wentong.pre.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class NumberGenerator {



    @GetMapping("number")
    public String number() {
        return "123";
    }

}
