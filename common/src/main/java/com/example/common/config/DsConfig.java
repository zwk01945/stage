package com.example.common.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.common.config.properter.DsProperter;
import com.example.common.config.propertie.DsProperties;
import com.example.common.dynamicds.DynamicDatasource;
import com.example.common.dynamicds.transcation.MultiDataSourceTransactionFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.common.config.properter.DsProperter.PROPERTIES_START;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base                                *
 *                                                            *
 *         File Name : DsConfig.java                          *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/24 17:28                       *
 *                                                            *
 *         Last Update : 2020/7/24 17:28                      *
 *                                                            *
 *------------------------------------------------------------*
 * 功能:                                                       *
 *   多数据源配置                                               *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Configuration
public class DsConfig{

    private static final Logger log = LoggerFactory.getLogger(DsConfig.class);


    private MybatisPlusProperties properties;


    private ResourceLoader resourceLoader = new DefaultResourceLoader();


    private Interceptor[] interceptors;


    private DatabaseIdProvider databaseIdProvider;

    @Autowired
    public void setProperties(MybatisPlusProperties properties) {
        this.properties = properties;
    }
    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Autowired(required = false)
    public void setInterceptors(Interceptor[] interceptors) {
        this.interceptors = interceptors;
    }
    @Autowired(required = false)
    public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
        this.databaseIdProvider = databaseIdProvider;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }



    public DataSource getMasterDatasoure(DsProperties master) throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(master.getJdbcUrl());
        dataSource.setUsername(master.getUsername());
        dataSource.setPassword(master.getPassword());
        dataSource.setDriverClassName(master.getDbName());
        return dataSource;
    }

    @Bean(name = "dynamicDataSource")
    public DataSource getDatasoure() throws SQLException {
        log.info("初始化数据源");
        DsProperter instance = DsProperter.getInstance();
        List<String> list = instance.getList(DsProperter.PROPERTIES_START);
        int num = list.size() / 4;
        DsProperties master = null;
        DynamicDatasource dynamicDataSource = new DynamicDatasource();
        Map<Object, Object> dbMap = new HashMap<Object, Object>();
        for (int i = 0; i < num; i++) {
            master = new DsProperties();
            if (i == 0) {
                log.info("初始化master数据源");
                master.setDbName(instance.getProperty(PROPERTIES_START + ".master.driver-class-name"));
                master.setJdbcUrl(instance.getProperty(PROPERTIES_START + ".master.url"));
                master.setUsername(instance.getProperty(PROPERTIES_START + ".master.username"));
                master.setPassword(instance.getProperty(PROPERTIES_START + ".master.password"));
                dynamicDataSource.setDefaultTargetDataSource(getMasterDatasoure(master));
                dbMap.put("master", getMasterDatasoure(master));
            } else {
                log.info("初始化slave"+i+"数据源");
                master.setDbName(instance.getProperty(PROPERTIES_START + ".slave_" + i + ".driver-class-name"));
                master.setJdbcUrl(instance.getProperty(PROPERTIES_START + ".slave_" + i + ".url"));
                master.setUsername(instance.getProperty(PROPERTIES_START + ".slave_" + i + ".username"));
                master.setPassword(instance.getProperty(PROPERTIES_START + ".slave_" + i + ".password"));
                dbMap.put("slave_"+i, getMasterDatasoure(master));
            }
        }
        dynamicDataSource.setTargetDataSources(dbMap);
        log.info("数据源初始化完成");
        return dynamicDataSource;
    }

//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean ();
//        bean.setDataSource(dataSource);
//        bean.setTransactionFactory(new MultiDataSourceTransactionFactory());
//        // 指定扫描的xml文件所在位置，在配置文件里面配置，会报Invalid bound statement
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:com/example/quartz/*.xml");
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        bean.setMapperLocations(resources);
//        bean.setConfiguration(configuration);
//        return bean.getObject();
//    }


    @Bean("mybatisSqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException, SQLException {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(getDatasoure());
        mybatisPlus.setTransactionFactory(new MultiDataSourceTransactionFactory());
        mybatisPlus.setVfs(SpringBootVFS.class);
        String configLocation = this.properties.getConfigLocation();
        if (StringUtils.isNotBlank(configLocation)) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(configLocation));
        }
        mybatisPlus.setConfiguration(properties.getConfiguration());
        mybatisPlus.setPlugins(this.interceptors);
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mc.setMapUnderscoreToCamelCase(false);// 数据库和java都是驼峰，就不需要
        mybatisPlus.setConfiguration(mc);
        if (this.databaseIdProvider != null) {
            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
        }
        mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
// 设置mapper.xml文件的路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("classpath:mapper/quartz/*.xml");
        mybatisPlus.setMapperLocations(resource);
        return mybatisPlus;
    }

    
    
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mybatisSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
