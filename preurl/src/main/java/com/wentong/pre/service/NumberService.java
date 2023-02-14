package com.wentong.pre.service;

import org.springframework.stereotype.Service;

/**
 * 数字生成服务
 * todo 需要有一个后台进程监听文件的数量，如果小于 5 个，生成文件。
 * todo 这个服务就是加载文件的内容到内存中，并且删除文件。如果重启，则将内存中的丢弃，考虑到系统不会经常重启，丢弃部分也无所谓（也可以降低每一个文件存储的大小）
 *
 */
@Service
public class NumberService {

    public String getNumber() {
        return "123";
    }

}
