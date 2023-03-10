package com.wentong.pre.gather;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.pre.filter.Filter;
import com.wentong.pre.filter.GuavaBloomFilter;
import com.wentong.pre.generator.Base62Generator;
import com.wentong.pre.generator.UrlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static com.wentong.constants.Constants.BASE_DIR;

@Component
@Slf4j
public class UrlGather {

    private static final int EVERY = 1000000;

    public List<String> gather() {
        UrlGenerator generator = new Base62Generator();
        Filter filter = new GuavaBloomFilter();

        int counter = 0;

        StringBuilder stringBuilder = new StringBuilder(6 * EVERY);

        for (int i = 0; i < EVERY; i++) {
            String generate = generator.generate();
            boolean notContains = filter.filter(generate);
            if (!notContains) {
                counter++;
            } else {
                // retry three times
                for (int j = 0; j < 3; j++) {
                    generate = generator.generate();
                    notContains = filter.filter(generate);
                    if (!notContains) {
                        counter++;
                        break;
                    }
                }
            }
            stringBuilder.append(generate);
        }


        log.info("开始写入文件");
        boolean exist = FileUtil.exist(BASE_DIR);
        if (!exist) {
            log.info("创建指定文件夹：" + BASE_DIR);
            boolean ok = FileUtil.mkdirsSafely(new File(BASE_DIR), 2, 1000);
            if (!ok) {
                log.info("创建文件夹失败");
                System.exit(1);
            }
            log.info("创建文件夹成功");
        }

        Optional<String> max = FileUtil.listFileNames(BASE_DIR).stream().max(String::compareTo);
        String data = stringBuilder.toString();
        if (max.isPresent()) {
            String s = max.get();
            long maxSize = Long.parseLong(s);
            long thisSize = maxSize + counter;
            FileUtil.writeString(data, BASE_DIR + thisSize, "utf-8");
        } else {
            FileUtil.writeString(data, BASE_DIR + counter, "utf-8");
        }
        String[] cut = StrUtil.cut(data, 6);
        log.info("加载到的数据个数：{}",cut.length);
        return CollUtil.newArrayList(cut);
    }

}
