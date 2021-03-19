package io.lhj.rpcfx.demo.consumer.aspect;

import io.lhj.rpcfx.annotation.Reference;
import io.lhj.rpcfx.client.Rpcfx;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/18
 */
@Aspect
@Component
public class ReferenctAspect {

    /**
     * 除了此种办法还可以通过自定义BeanPostProcessor来完成，更加优雅，不用在consumer端添加代码
     * @param joinPoint
     * @throws Exception
     */
    @Before("execution(* io.lhj.rpcfx.demo.consumer..*.*(..))")
    public void requestLimit(JoinPoint joinPoint) throws Exception {
        Object target = joinPoint.getTarget();//获取当前类对象
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //判断当前字段是否有@Reference注解
            Reference reference = field.getAnnotation(Reference.class);
            if(reference != null){
                //判断当前字段是否为空
                if(field.get(target) == null){
                    //生成代理对象
                    field.set(target , Rpcfx.create(field.getType() , reference.url()));
                }
            }
        }
    }

}
