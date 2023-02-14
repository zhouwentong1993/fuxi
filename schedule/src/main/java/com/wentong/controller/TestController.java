//package com.wentong.controller;
//
//import com.google.common.collect.Lists;
//import com.wentong.loader.Loader;
//import com.wentong.saver.Saver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    @Autowired
//    private Loader loader;
//
//    @Autowired
//    private Saver saver;
//
//    @GetMapping("/url")
//    public String getUrl() {
//        saver.save("http://www.baidu.com");
//        return "ok";
//    }
//
//    @GetMapping("load")
//    public String load() {
//        List<String> load = loader.load();
//        List<List<String>> partition = Lists.partition(load, 10000);
//        partition.forEach(saver::saveBatch);
//        return "ok";
//    }
//
//}
