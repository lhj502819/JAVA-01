package method;

import field.DependencyInjectionAnnotationByField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.User;
import pojo.UserHolder;

import javax.annotation.Resource;

/**
 * 基于Java 注解实现依赖方法注入示例
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectionByAnnotationMethod {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void init1(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolder){
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(DependencyInjectionByAnnotationMethod.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResoucePath = "classpath:/META-INF/dependency-injection.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResoucePath);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
//        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        DependencyInjectionByAnnotationMethod demo = applicationContext.getBean(DependencyInjectionByAnnotationMethod.class);
        System.out.println(demo.userHolder);
        System.out.println(demo.userHolder2);
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
