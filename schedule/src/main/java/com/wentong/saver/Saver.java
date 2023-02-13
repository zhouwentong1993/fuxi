package com.wentong.saver;

import java.util.List;

public interface Saver {

    void save(String url);

    void saveBatch(List<String> urls);

}
