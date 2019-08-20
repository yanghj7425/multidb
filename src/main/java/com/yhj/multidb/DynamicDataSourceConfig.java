package com.yhj.multidb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : yhj
 * @version : 1.0 $
 * @date : Created in 2019/8/20 14:41
 * @description : 动态数据源配置类
 * @modified By :
 */
@Configuration
@Import(DynamicDataSourceProperties.class)
@ComponentScan
public class DynamicDataSourceConfig {
}
