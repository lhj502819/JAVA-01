package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.annotation;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.constant.OperationEnum;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author lihongjian
 * @since 2021/3/6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {
    String value() default "";  //数据源名称

    OperationEnum operate();  //操作类型 读、写
}
