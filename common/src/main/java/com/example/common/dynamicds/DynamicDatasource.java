package com.example.common.dynamicds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName: DynamicDatasource
 * Description:
 * date: 2020/7/21 13:12
 *
 * @author zwk
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }
}
