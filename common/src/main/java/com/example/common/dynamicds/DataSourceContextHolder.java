package com.example.common.dynamicds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * ClassName: DataSourceContextHolder
 * Description:
 * date: 2020/7/21 13:12
 *
 * @author zwk
 */
public class DataSourceContextHolder {
    public static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);
    public static final String DEFAULT_DS="master";
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDbType (String dbType) {
        logger.info("切换数据库:" + dbType);
        contextHolder.set(dbType);
    }
    public static String getDbType () {
        String dbType = (String) contextHolder.get();
        if (StringUtils.isEmpty(dbType)) {
            dbType = DEFAULT_DS;
        }
        return dbType;
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
