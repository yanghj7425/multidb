package com.yhj.multidb.aop.aspect;

import com.yhj.multidb.DynamicDataSource;
import com.yhj.multidb.aop.annotation.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yhj
 * @date ：Created in 2019/8/20 11:08
 * @description: AOP 拦截器
 * @modified By :
 * @version: 1.0$
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    private static Map<MethodSignature, String> cacheAspect = new HashMap<>();


    @Pointcut("@annotation(com.yhj.multidb.aop.annotation.DynamicRoutingDataSource)")
    public void pointCut() {
    }

    /**
     * <pre>
     *     动态切换数据源
     * </pre>
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String dataSourceName = cacheAspect.get(signature);

        if (!StringUtils.isEmpty(dataSourceName)) {
            DynamicDataSource.setDataSourceKey(dataSourceName);
            return;
        }

        Class clazz = signature.getDeclaringType();
        Class[] parameterTypes = signature.getParameterTypes();
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DynamicRoutingDataSource.class)) {
                DynamicRoutingDataSource dataSource = method.getAnnotation(DynamicRoutingDataSource.class);
                dataSourceName = dataSource.name();
                cacheAspect.put(signature, dataSourceName);
            }
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e.getCause());
        }
    }


    /**
     * 使用切换为默认数据源
     */
    @After("pointCut()")
    public void clear() {
        DynamicDataSource.clear();
    }


}
