package com.wentong.pre.filter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 */
public class GuavaBloomFilter implements Filter {

    // 100w 个 url，误判率 0.0001
    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000, 0.0001);

    @Override
    public boolean filter(String url) {
        bloomFilter.put(url);
        return bloomFilter.mightContain(url);
    }
}
