公司使用了sharding-jdbc做分库分表，因此使用sharding-proxy来做测试

* sharding-proxy的配置文件为server.yaml
* 分片配置文件为config-sharding.yaml
* 初始化sql脚本为init.sql，数据库为order_db0和order_db1