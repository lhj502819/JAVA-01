package api;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.UserHolder;

/**
 * 基于Java API实现依赖Constructor方法注入示例
 *
 * @author lihongjian
 * @since 2021/2/16
 */
public class DependencyInjectionApiByConstructor {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //生成UserHolder 的 BeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        //注册UserHolder 的 BeanDefinition
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResoucePath = "classpath:/META-INF/dependency-injection.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResoucePath);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
        //关闭应用上下文
        applicationContext.close();
    }

    /**
     * 为{@link UserHolder} 生成
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //使用构造器引用的方式注入，是有顺序的，如果有多个参数，那么会根据构造器参数的顺序依次进行注入
        beanDefinitionBuilder.addConstructorArgReference("user");
        beanDefinitionBuilder.addConstructorArgValue("helloWorld");

        return beanDefinitionBuilder.getBeanDefinition();
    }

    @Bean
    public String helloWorld(){
        return "helloWorld";
    }
}
