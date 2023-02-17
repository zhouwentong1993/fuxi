package com.wentong.pre.controller;

import com.wentong.pre.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/number")
public class NumberGenerator {

    @Autowired
    private NumberService numberService;


    @GetMapping("generator")
    public String number(@RequestParam("sourceUrl") String sourceUrl) {
        return numberService.getNumber(sourceUrl);
    }

}
