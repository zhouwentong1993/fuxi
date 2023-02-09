# 简介
伏羲，短链系统。
满足高并发、高可用、高性能等特性。设计思路参考了[短链系统设计](https://time.geekbang.org/column/article/488496)。
# 实现
设计思路如下：https://www.processon.com/mindmap/63e453489fe2183952f8aaf1
系统由入口核心模块：core、预生成 URL模块：preurl，定时器模块：schedule，整体测试模块：test 组成。