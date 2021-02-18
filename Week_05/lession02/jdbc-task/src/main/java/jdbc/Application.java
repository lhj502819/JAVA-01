package jdbc; /**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/18
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lihongjian
 * @since 2021/2/17
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
    }
}
