package foreignexchange.freeze;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihongjian
 * @since 2020/2/18
 */
@MapperScan(basePackages = {"foreignexchange.freeze.mapper"})
@EnableDubbo(scanBasePackages = "foreignexchange.freeze.service")
@SpringBootApplication
public class FreezeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreezeApplication.class , args);
    }
}
