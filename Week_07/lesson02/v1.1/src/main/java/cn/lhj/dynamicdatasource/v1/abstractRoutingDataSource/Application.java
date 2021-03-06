package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/3/4
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "cn.lhj.dynamicdatasource.v1.mapper")
public class Application {
    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }
}
