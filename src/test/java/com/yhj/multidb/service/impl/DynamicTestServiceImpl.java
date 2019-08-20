package com.yhj.multidb.service.impl;

import com.yhj.multidb.aop.annotation.DynamicRoutingDataSource;
import com.yhj.multidb.service.DynamicTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : yhj
 * @version :  1$
 * @date : Created in 2019/8/20 11:57
 * @description : 动态数据源测试Service实现
 * @modified By :
 */
@Service
public class DynamicTestServiceImpl implements DynamicTestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DynamicRoutingDataSource(name = "default")
    @Override
    public List<Map<String, Object>> queryCellDbTest() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT    * FROM   product_info;");
        return mapList;
    }

    @DynamicRoutingDataSource(name = "mini")
    @Override
    public List<Map<String, Object>> queryMiniDbTest() {
        return jdbcTemplate.queryForList("SELECT    * FROM   sys_user;");
    }
}
