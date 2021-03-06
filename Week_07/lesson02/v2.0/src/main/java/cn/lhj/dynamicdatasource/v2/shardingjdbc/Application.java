package cn.lhj.dynamicdatasource.v2.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/3/4
 */

@MapperScan(basePackages = "cn.lhj.dynamicdatasource.v2.shardingjdbc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }
}
