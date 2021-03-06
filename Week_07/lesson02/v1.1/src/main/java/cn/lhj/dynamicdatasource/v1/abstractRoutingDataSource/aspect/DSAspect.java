package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.aspect;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.annotation.DS;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.constant.DBConstant;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.dyncamic.DataSourceContainer;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 实现拦截 {@link cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.annotation.DS} 设置数据源
 *
 * @author lihongjian
 * @since 2021/3/6
 */
@Aspect
@Component
public class DSAspect implements ApplicationRunner {

    List<String> slaveDS = new ArrayList<>();

    @Around(value = "(@annotation(ds))")
    public Object dsAround(ProceedingJoinPoint proceedingJoinPoint, DS ds) throws Throwable {
        try {
            String value = "";
            if (ds.operate() == OperationEnum.WRITE) {
                value = ds.value();
            } else { //读操作
                if (StringUtils.isNotBlank(ds.value())) {
                    value = ds.value();
                } else {
                    Random random = new Random();
                    value = slaveDS.get(random.nextInt(slaveDS.size()));
                }
            }
            DataSourceContainer.setDataSource(value);
            //写入线程应该用哪个DS
            return proceedingJoinPoint.proceed();
        } finally {
            //清空上下文信息
            DataSourceContainer.clear();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        slaveDS.add(DBConstant.DATASOURCE_SLAVE1);
        slaveDS.add(DBConstant.DATASOURCE_SLAVE2);
    }
}
