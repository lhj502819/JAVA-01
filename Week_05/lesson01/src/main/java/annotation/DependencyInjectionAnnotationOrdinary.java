package annotation;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pojo.User;
import pojo.UserHolder;

/**
 * 通过注解的方式自动注入
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectionAnnotationOrdinary {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(DependencyInjectionAnnotationOrdinary.class);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User(1L,25 , "lihongjian");
        return user;
    }

}
