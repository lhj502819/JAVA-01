package foreignexchange.account.userB;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihongjian
 * @since 2020/2/18
 */
@MapperScan(basePackages = {"foreignexchange.account.userB.mapper"})
@EnableDubbo(scanBasePackages = "foreignexchange.account.userB.service")
@SpringBootApplication
public class UserBAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBAccountApplication.class , args);
    }
}
