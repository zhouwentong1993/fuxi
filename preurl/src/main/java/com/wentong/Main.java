package com.wentong;

import cn.hutool.core.io.FileUtil;
import com.google.common.base.Stopwatch;
import com.wentong.pre.filter.Filter;
import com.wentong.pre.filter.GuavaBloomFilter;
import com.wentong.pre.generator.Base62Generator;
import com.wentong.pre.generator.UrlGenerator;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int EVERY = 1000000;
    private static final String BASE_DIR = "/Users/wentong/fuxi/";

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        UrlGenerator generator = new Base62Generator();
        Filter filter = new GuavaBloomFilter();

        int counter = 0;

        StringBuilder stringBuilder = new StringBuilder(6 * EVERY);

        for (int i = 0; i < EVERY; i++) {
            String generate = generator.generate();
            boolean notContains = filter.filter(generate);
            if (notContains) {
                counter++;
            }
            stringBuilder.append(generate);
        }

        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("生成 " + counter + " 个字符串耗时：" + elapsed + "ms");

        System.out.println("开始写入文件");
        boolean exist = FileUtil.exist(BASE_DIR);
        if (!exist) {
            System.out.println("创建指定文件夹：" + BASE_DIR);
            boolean ok = FileUtil.mkdirsSafely(new File(BASE_DIR), 2, 1000);
            if (!ok) {
                System.out.println("创建文件夹失败");
                System.exit(1);
            }
            System.out.println("创建文件夹成功");
        }

        Optional<String> max = FileUtil.listFileNames(BASE_DIR).stream().max(String::compareTo);
        if (max.isPresent()) {
            String s = max.get();
            long maxSize = Long.parseLong(s);
            long thisSize = maxSize + counter;
            FileUtil.writeString(stringBuilder.toString(), BASE_DIR + thisSize, "utf-8");
        } else {
            FileUtil.writeString(stringBuilder.toString(), BASE_DIR + counter, "utf-8");
        }


    }
}