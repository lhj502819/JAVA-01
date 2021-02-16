package callback;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.User;

/**
 * 通过 {@link ApplicationContextAware }回调的方式进行注入
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectionByApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(DependencyInjectionByApplicationContextAware.class);

        context.refresh();

        System.out.println(applicationContext);
        System.out.println(applicationContext.getBean("user"));
        context.close();

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DependencyInjectionByApplicationContextAware.applicationContext = applicationContext;
    }

    @Bean
    public User user(){
        User user = new User(1L,25 , "lihongjian");
        return user;
    }
}
