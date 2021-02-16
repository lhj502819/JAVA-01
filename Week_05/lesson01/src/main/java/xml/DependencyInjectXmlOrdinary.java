package xml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

/**
 * 通过XML自动注入
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectXmlOrdinary {
    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection.xml");
        User user = beanFactory.getBean("user", User.class);
        System.out.println("从容器中获取到User对象" + user);

    }


}
