package com.yhj.multidb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yhj
 * @date : Created in 2019/8/20 11:08
 * @description : 数据源类
 * @modified By :
 * @version: 1.0$
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();


    private DynamicDataSource() {
    }

    public static void clear() {
        dataSourceKey.remove();
    }

    void setDefaultTargetDataSource(DbProperty property) {
        if (property.isDefaultDb()) {
            super.setDefaultTargetDataSource(property.getDataSource());
        }
    }

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }


    static class Builder {
        private List<DbProperty> dbProperties;

        Builder(List<DbProperty> dbProperties) {
            this.dbProperties = dbProperties;
        }

        DynamicDataSource builder() {
            Assert.isTrue(!dbProperties.isEmpty(), "【数据源配置】 读取不到数据源信息, dbProperties 不能为空");

            DynamicDataSource dynamicDataSource = new DynamicDataSource();
            Map<Object, Object> dynamicDataSourceMap;

            dynamicDataSourceMap = new HashMap<>();
            for (DbProperty dbProperty : dbProperties) {
                dynamicDataSource.setDefaultTargetDataSource(dbProperty);
                dynamicDataSourceMap.put(dbProperty.getBeanName(), dbProperty.getDataSource());
            }
            dynamicDataSource.setTargetDataSources(dynamicDataSourceMap);
            return dynamicDataSource;
        }

    }


}
