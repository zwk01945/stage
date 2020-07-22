# stage
单体项目多module脚手架，整合多mybatis-plus数据源，基础util等工具，nginx负载均衡等
便于单体项目快速开发。
#### common
- 包括全局异常
    
   位于com.example.common.exception包下，需要注意的是ControllerAdvice需要被别的模块扫描到，所以需要在别的
   启动类上指定扫描的包，最好是创建包名的大类和common一致，不然会失效.
   
   使用方式可以创建自定义Exception，在ControllerAdvice中使用ExceptionHandler指定具体的异常,给出具体的返回
   
- 全局拦截器

    自定义Handler去实现HandlerInterceptor,会在每个请求过来的时候进入处理，写完还需要在WebConfig中配置，将
    拦截器添加到MVC的流程中，否则不生效
    
    包括一些CORS跨域的后端处理
    
- 共有bean和controller

    bean下分模块存放实体类，controller提取所有业务的共同控制器
    
    
- 全局初始化配置

    全局初始化包括输出项目所需要的各种配置文件输出到实体类中，以日志形式打出
    
    
- 多数据源配置

- redis配置   

  集群搭建redis,封装一些常用操作
  
        redisTemplate.opsForValue();　　//操作字符串
        redisTemplate.opsForHash();　　 //操作hash
        redisTemplate.opsForList();　　 //操作list
        redisTemplate.opsForSet();　　  //操作set
        redisTemplate.opsForZSet();　 　//操作有序set
        stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间  
        
        stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作
        
        stringRedisTemplate.opsForValue().get("test")//根据key获取缓存中的val
        
        stringRedisTemplate.boundValueOps("test").increment(1);//val +1
        
        stringRedisTemplate.getExpire("test")//根据key获取过期时间
        
        stringRedisTemplate.getExpire("test",TimeUnit.SECONDS)//根据key获取过期时间并换算成指定单位 
        
        stringRedisTemplate.delete("test");//根据key删除缓存
        
        stringRedisTemplate.hasKey("546545");//检查key是否存在，返回boolean值 
        
        stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合
        
        stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间
        
        stringRedisTemplate.opsForSet().isMember("red_123", "1")//根据key查看集合中是否存在指定数据
        
        stringRedisTemplate.opsForSet().members("red_123");//根据key获取set集合
  
  需要连接redis的模块，需要在自己的配置文件中写上redis的集群配置节点
#### quartz
- 定时任务模块
- 日志记录
