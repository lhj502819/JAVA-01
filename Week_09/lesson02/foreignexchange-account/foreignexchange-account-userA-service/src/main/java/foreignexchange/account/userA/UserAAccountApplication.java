package foreignexchange.account.userA;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihongjian
 * @since 2020/2/18
 */
@MapperScan(basePackages = {"foreignexchange.account.userA.mapper"})
@EnableDubbo(scanBasePackages = "foreignexchange.account.userA.service")
@SpringBootApplication
public class UserAAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAAccountApplication.class , args);
    }
}
