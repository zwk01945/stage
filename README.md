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
#### quartz
- 定时任务模块
- 日志记录