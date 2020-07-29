### spring-exclusion
一、`InitialingBean接口` <br>
    bean初始化之后执行,用来给bean初始化.
    
    @Component
    public class SysInit implements InitializingBean {
    @Override
        public void afterPropertiesSet() throws Exception {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement boot = stackTrace[stackTrace.length -1];
            }
    }        
二、`DisposableBean接口` <br>
    bean销毁之前执行,用来释放一些资源
    
    public class DataSourceContextHolder implements DisposableBean {
    @Override
        public void destroy() throws Exception {
            contextHolder.remove();
        }
    }    
三、 `CommandLineRunner接口(ApplicationRunner)` <br>   
    SpringBoot的扩展,在程序启动以后执行,用于初始化热点数据等
    
    @Component
    public class RedisInit implements CommandLineRunner {
    @Override
        public void run(String... args) throws Exception {
            log.info("初始化Quartz热点缓存数据==============");
            List<IcpCode> icpCodes = icpCodeService.selectAll();
            String icp_code = redisTemplate.opsForValue().get("icp_code1");
            log.info("热点缓存数据存放成功:{}",icp_code);
        }
    }
四、 `BeanNameAware,ApplicationContextAware` <br>   
    bean加载的过程中获取到bean的Id,获取到容器上下文
    
    @Component
    public class SpringBeanUtils implements BeanNameAware, ApplicationContextAware {
    @Override
        public void setBeanName(String s) {
            System.out.println(s);
        }
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if (SpringBeanUtils.context == null) {
                SpringBeanUtils.context = applicationContext;
            }
        }
    }
五、`BeanPostProcess` <br>
    每个bean实例化前后执行<br>
六、`AbstractRoutingDataSource`<br>
    动态数据源切换数据源
七、WebMvcConfigurer(SpringBoot对webmvc的扩展)<br>

    addResourceHandlers 映射请求静态资源
    addInterceptors  添加拦截器
    addCorsMappings  配置跨域请求
    addViewControllers  配置视图控制器
    addFormatters   请求参数的格式转换比如日期
    configureMessageConverters 消息转换器
    configureViewResolvers    配置视图转换器
    
    