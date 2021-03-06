使用Spring的AbstractRoutingDataSource实现多数据源、读写分离

* 基于操作 AbstractRoutingDataSource 和自定义注解 readOnly 之
  类的，简化自动切换数据源
  
* 支持配置多个从库

* 支持多个从库的负载均衡