package com.wentong.pre.loader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.metrics.ApplicationStartUp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.wentong.constants.Constants.BASE_DIR;

@Component
@Slf4j
public class FileLoader implements Loader {

    @Override
    public List<String> load() {
        ApplicationStartUp applicationStartUp = ApplicationStartUp.DEFAULT;
        applicationStartUp.start();
        List<String> fileNames = FileUtil.listFileNames(BASE_DIR);
        if (CollUtil.isEmpty(fileNames)) {
            log.info("没有文件");
            return Collections.emptyList();
        }

        fileNames.sort(String::compareTo);
        String filePath = BASE_DIR + fileNames.get(0);

        List<String> lines = FileUtil.readLines(filePath, "utf-8");
        if (CollUtil.isEmpty(lines)) {
            log.info("没有数据");
            return Collections.emptyList();
        }
        String allData = lines.get(0);
        String[] cut = StrUtil.cut(allData, 6);
        log.info("加载到的数据个数：{}",cut.length);
        applicationStartUp.stop();
        FileUtil.del(filePath);
        log.info("删除文件：{}", filePath);
        return CollUtil.newArrayList(cut);
    }

}
