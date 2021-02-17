package student.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Student自动装配类
 *
 * @author lihongjian
 * @since 2021/2/17
 */
@Configuration
@ConditionalOnClass(Student.class)
//@ConditionalOnProperty(prefix = "student" ,name = "enable" , havingValue = "true")
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

    @Bean
    public Student student(StudentProperties studentProperties){
        if(studentProperties.getId() == null){
            studentProperties.setId(1);
        }
        if(!StringUtils.hasText(studentProperties.getName())){
            studentProperties.setName("无名氏");
        }
        return new Student(studentProperties.getId() , studentProperties.getName());
    }

}
