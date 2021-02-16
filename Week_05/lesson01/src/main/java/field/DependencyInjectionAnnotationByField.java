package field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.User;
import pojo.UserHolder;

/**
 * 通过注解的方式 字段自动注入
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectionAnnotationByField {


    @Autowired
    private UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(DependencyInjectionAnnotationByField.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResoucePath = "classpath:/META-INF/dependency-injection.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResoucePath);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
//        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        DependencyInjectionAnnotationByField demo = applicationContext.getBean(DependencyInjectionAnnotationByField.class);
        System.out.println(demo.userHolder);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
