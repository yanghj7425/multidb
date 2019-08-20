# multidb
通过 AbstractRoutingDataSource + AOP 实现多数据源


    
- yml 配置方式
    ```
    spring:
      multi-datasource:
        db-properties[0]:
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/sell?useUnicode=true&characterEncoding=utf8&useSSL=true&allowMultiQueries=true&serverTimezone=UTC
          username: root
          password: 123456
          bean-name: default
    
        db-properties[1]:
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/mini?useUnicode=true&characterEncoding=utf8&useSSL=true&allowMultiQueries=true&serverTimezone=UTC
          username: root
          password: 123456
          bean-name: mini
    ```
    - 字段说明
      
      |   key    |         说明      |
      |:---------|:----------------|
      |    type   |  连接池类型(暂无使用)|
      |driver-class-name| 驱动类名称 |         
      | url         | 数据库地址     |
      | username    | 用户名         |
      | password    | 密码          |            
      | bean-name   | bean 名称，通过 DynamicRoutingDataSource 注解选择使用对应的数据源|    
     > bean-name 为 default 时为默认数据源，若 bean-name 重复出现，则后面覆盖前面 
 - 引用方式
     - 引入模块
     ```java
       @SpringBootApplication
       @Import(DynamicDataSourceConfig.class)
       public class DemoApplication {
       
           public static void main(String[] args) {
               SpringApplication.run(DemoApplication.class, args);
           }
       
       }
     ```
     - 使用: 通过注解在 Service 方法上拦截。
        ```java
           @Override
           @DynamicRoutingDataSource(name = "mini")
           public List<SysUser> queryAll() {
               return super.queryAll();       
            }
        ```
       - 若不使用注解 @DynamicRoutingDataSource 拦截则使用 yml 中指定的默认数据源。     
      
         