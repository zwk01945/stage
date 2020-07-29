### boot
一、SpringBoot自动配置
- `@SpringBootApplication`

    excludeClass,scanBasePackages,geneBeanName
    
- `@EnableAutoConfiguration`
    exclude,excludeName    
- `@AutoConfigurationPackage`
    目的是获取到主启动类，并以此为默认包名去扫描注解
- `@Import({AutoConfigurationImportSelector.class})` 
        
    1.ImportSelector的接口方法selectImports是将需要注入容器的class以数组形式返回<br>
    2.@Import是将指定的bean都注入到容器中<br> 
        有三种方式，直接Import(X.class)  Import(ImportSelector.class) Import(Configuration)<br>
    3.AutoConfigurationImportSelector 实现了 DeferredImportSelector 实现了  ImportSelector(selectImports,getExclusionFilter)<br>
    4.在selectImports方法中，判断了是否配置了开启自动配置，如果没有就默认true，不然就按照配置文件配置的写,为false的话就renturn空的bean数组<br>
    5.getAttributes() 找到开启的注解类下的需要排除的类<br> 
    6.getAutoConfigurationEntry()方法 获取到所有需要注入的entry，最后过滤不需要的<br>
    7.getCandidateConfigurations()方法  真正的去获取Classloader下所有jar里面的spring.factories中需要加载的类<br>
二、SpringBoot提供的配置方式和读取方式
- ConfigurationProperties(prefix="") application.properties文件 <br>
- @PropertySource(value = "classpath:sys.properties")  指定自己配置的配置文件<br>
- @Configuration方式使用@Bean注入bean到容器<br>
三、 SpringBoot其他优势
- 使用Starter去管理依赖,减少版本依赖冲突等问题
- 内置tomcat容器可以简化部署
- 支持RESTFULAPI和socket等
- 可以自定义banner，只需要在resource下创建banner.txt，就可以    