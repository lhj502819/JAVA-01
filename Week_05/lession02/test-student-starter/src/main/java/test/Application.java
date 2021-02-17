package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import student.starter.Student;

/**
 * @author lihongjian
 * @since 2021/2/17
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        Student bean = run.getBean(Student.class);
        System.out.println(bean);
    }
}
