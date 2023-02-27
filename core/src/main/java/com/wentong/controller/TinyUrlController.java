package com.wentong.controller;

import com.wentong.service.TinyUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Controller
@RequestMapping("/tinyurl")
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    public TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @GetMapping("/url")
    @ResponseBody
    public String tiny(@RequestParam("sourceUrl") String sourceUrl) {
        return tinyUrlService.transUrl(sourceUrl);
    }

    @GetMapping("/visit")
    public ResponseEntity<Void> visit(@RequestParam("tinyUrl") String tinyUrl) {
        String realUrl = tinyUrlService.visit(tinyUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(realUrl)).build();
    }
}