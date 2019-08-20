package com.yhj.multidb;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ：yhj
 * @date ：Created in 2019/8/20 11:08
 * @description: 数据源属性映射类
 * @modified By :
 * @version: 1.0$
 */
@Configuration
@ConfigurationProperties(
        prefix = "spring.multi-datasource"
)
@ToString
public class DynamicDataSourceProperties {

    private List<DbProperty> dbProperties;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        return new DynamicDataSource.Builder(dbProperties).builder();
    }


    public void setDbProperties(List<DbProperty> dbProperties) {
        this.dbProperties = dbProperties;
    }

}
