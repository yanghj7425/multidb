package com.yhj.multidb.service;

import java.util.List;
import java.util.Map;

/**
 * @author : yhj
 * @version :  1$
 * @date : Created in 2019/8/20 11:56
 * @description : 动态数据源测试Service
 * @modified By :
 */
public interface DynamicTestService {
    List<Map<String, Object>> queryCellDbTest();

    List<Map<String, Object>> queryMiniDbTest();
}
