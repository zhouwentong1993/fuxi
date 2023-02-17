package com.wentong.controller;

import com.wentong.service.TinyUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/visit/{tinyUrl}")
    public ResponseEntity<Void> visit(@PathVariable String tinyUrl, HttpServletResponse response) throws IOException {
        String realUrl = tinyUrlService.visit(tinyUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(realUrl)).build();
    }
}