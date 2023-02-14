package com.wentong.pre.loader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.metrics.ApplicationStartUp;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.wentong.constants.Constants.BASE_DIR;

@Component
public class FileLoader implements Loader {

    @Override
    public List<String> load() {
        ApplicationStartUp applicationStartUp = ApplicationStartUp.DEFAULT;
        applicationStartUp.start();
        List<String> fileNames = FileUtil.listFileNames(BASE_DIR);
        if (CollUtil.isEmpty(fileNames)) {
            System.out.println("没有文件");
            return Collections.emptyList();
        }

        fileNames.sort(String::compareTo);
        String filePath = BASE_DIR + fileNames.get(0);

        List<String> lines = FileUtil.readLines(filePath, "utf-8");
        if (CollUtil.isEmpty(lines)) {
            System.out.println("没有数据");
            return Collections.emptyList();
        }
        String allData = lines.get(0);
        String[] cut = StrUtil.cut(allData, 6);
        System.out.println(cut.length);
        applicationStartUp.stop();
        return CollUtil.newArrayList(cut);
    }

}
