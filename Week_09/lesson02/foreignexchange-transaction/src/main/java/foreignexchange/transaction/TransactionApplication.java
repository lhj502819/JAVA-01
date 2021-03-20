package foreignexchange.transaction;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihongjian
 * @since 2020/2/18
 */
@EnableDubbo(scanBasePackages = "foreignexchange.transaction.service")
@MapperScan(basePackages = {"foreignexchange.transaction.mapper"})
@SpringBootApplication
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class , args);
    }
}
