package com.yhj.multidb.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author ：yhj
 * @date ：Created in 2019/8/20 11:08
 * @description: 动态数据源注解
 * @modified By :
 * @version: 1.0$
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicRoutingDataSource {
    String name() default "";
}
