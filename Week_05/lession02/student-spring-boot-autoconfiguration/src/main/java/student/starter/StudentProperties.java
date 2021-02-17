package student.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Student配置类
 *
 * @author lihongjian
 * @since 2021/2/17
 */
@Data
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private Integer id;

    private String name;

}
