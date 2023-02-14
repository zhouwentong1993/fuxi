package com.wentong.pre.loader;

import java.util.List;

public interface Loader {

    /**
     * 加载指定数据
     * @return 加载的数据量
     */
    List<String> load();

}
