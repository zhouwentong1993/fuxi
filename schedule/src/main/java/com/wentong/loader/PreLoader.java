package com.wentong.loader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.wentong.metrics.ApplicationStartUp;

import java.util.List;

import static com.wentong.constants.Constants.BASE_DIR;

public class PreLoader {

    public static void main(String[] args) {
        ApplicationStartUp applicationStartUp = ApplicationStartUp.DEFAULT;
        applicationStartUp.start();
        List<String> fileNames = FileUtil.listFileNames(BASE_DIR);
        if (CollUtil.isEmpty(fileNames)) {
            System.out.println("没有文件");
            return;
        }
        fileNames.sort(String::compareTo);
        String filePath = BASE_DIR + fileNames.get(0);

        FileUtil.readLines(filePath, "utf-8").forEach(
                f -> {

                }
        );

        applicationStartUp.stop();
    }

}
