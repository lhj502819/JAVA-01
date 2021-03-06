package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.config;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.constant.DBConstant;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.dyncamic.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/6
 */
@Configuration
@MapperScan(basePackages = "cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.mapper" ,sqlSessionFactoryRef ="sqlSessionFactory" )
public class DynamicDataSourceConfig {

    private static final String ENTITY_PACKAGE = "cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.entity";

    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    /*=========================master==============================*/

    @Value("${spring.datasource.order-master.url}")
    private String masterUrl;

    @Value("${spring.datasource.order-master.driver-class-name}")
    private String masterDriverClassName;

    @Value("${spring.datasource.order-master.username}")
    private String masterUserName;

    @Value("${spring.datasource.order-master.password}")
    private String masterPassword;

    /*==========================slave1=============================*/

    @Value("${spring.datasource.order-slave1.url}")
    private String slave1Url;

    @Value("${spring.datasource.order-slave1.driver-class-name}")
    private String slave1DriverClassName;

    @Value("${spring.datasource.order-slave1.username}")
    private String slave1UserName;

    @Value("${spring.datasource.order-slave1.password}")
    private String slave1Password;
    
    /*==========================slave2=============================*/

    @Value("${spring.datasource.order-slave2.url}")
    private String slave2Url;

    @Value("${spring.datasource.order-slave2.driver-class-name}")
    private String slave2DriverClassName;

    @Value("${spring.datasource.order-slave2.username}")
    private String slave2UserName;

    @Value("${spring.datasource.order-slave2.password}")
    private String slave2Password;

    @Primary
    @Bean(name = DBConstant.DATASOURCE_MASTER)
    public DataSource masterDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.masterUrl);
        datasource.setUsername(masterUserName);
        datasource.setPassword(masterPassword);
        datasource.setDriverClassName(masterDriverClassName);
        return datasource;
    }
    @Primary
    @Bean(name = DBConstant.DATASOURCE_SLAVE1)
    public DataSource slaverDataSource1() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.slave1Url);
        datasource.setUsername(slave1UserName);
        datasource.setPassword(slave1Password);
        datasource.setDriverClassName(slave1DriverClassName);
        return datasource;
    }
    
    @Primary
    @Bean(name = DBConstant.DATASOURCE_SLAVE2)
    public DataSource slaverDataSourc2() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.slave2Url);
        datasource.setUsername(slave2UserName);
        datasource.setPassword(slave2Password);
        datasource.setDriverClassName(slave2DriverClassName);
        return datasource;
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(Map<String, DataSource> datasources) {
        Map<Object, Object> target = new HashMap<>(2);
        target.putAll(datasources);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(target);
        dataSource.setDefaultTargetDataSource(target.get(DBConstant.DATASOURCE_MASTER));
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DynamicDataSourceConfig.MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE);
        //mybatis数据库字段与实体类字段驼峰映射配置
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

}
