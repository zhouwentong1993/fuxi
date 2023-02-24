# 简介
伏羲，短链系统。
满足高并发、高可用、高性能等特性。设计思路参考了[短链系统设计](https://time.geekbang.org/column/article/488496)。
# 实现
设计思路如下：https://www.processon.com/mindmap/63e453489fe2183952f8aaf1
<br>
系统由入口核心模块：core、预生成 URL模块：preurl，定时器模块：schedule，整体测试模块：test 组成。
<br>
# 性能指标
本地 Jmeter 压测，8c 16G，启动 preUrl 和 core 项目。100 线程压测，每秒可生成 6000 次的短链，读取性能在 5w/qps。
# 核心设计
- 预生成短链 URL
- 布隆过滤器防止生成重复短链
- base62 算法，提升可读性
- Redis 缓存提升读取效率
- 预生成短链直接写入文件，100w 短链文件大小 5.4MB，紧缩格式，参考了 RocketMQ 存储设计
- 后台线程进行短链文件生成和短链队列填充
- 可分布式部署，布隆过滤器使用 Redis 实现即可