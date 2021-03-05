package cn.lhj.dynamicdatasource;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/3/4
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lihongjian
 * @since 2021/2/17
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.lhj.dynamicdatasource.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
    }
}
