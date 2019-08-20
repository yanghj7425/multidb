package com.yhj.multidb;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.sql.DataSource;


/**
 * @author ：yhj
 * @date ：Created in 2019/8/20 11:08
 * @description: 动态数据源属性
 * @modified By :
 * @version: 1.0$
 */
@ToString
@Getter
@Setter
class DbProperty {
    private String type;

    private String driverClassName;

    private String url;

    private String username;


    private String password;


    private String beanName;


    DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(getUrl());
        dataSource.setDriverClassName(getDriverClassName());
        dataSource.setUsername(getUsername());
        dataSource.setPassword(getPassword());
        return dataSource;
    }

    boolean isDefaultDb() {
        return "default".equals(getBeanName());
    }

}
