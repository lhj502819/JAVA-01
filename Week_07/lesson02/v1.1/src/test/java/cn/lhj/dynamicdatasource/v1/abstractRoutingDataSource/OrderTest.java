package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.entity.OrderDO;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lihongjian
 * @since 2021/3/6
 */
@Slf4j
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testUpdateOrder() {
        OrderDO orderDOUpdate = orderService.updateOrder(OrderDO.builder().productName("huawei-Meta40").id(1).build());
        log.info("[修改结果为{}]" , orderDOUpdate);
    }

    @Test
    public void testSelectOrderBySlave1() {
        OrderDO orderDO = orderService.selectOrder(1);
        log.info("[查询结果为{}]" , orderDO);
    }

    @Test
    public void testSelectOrderBySlave2() {
        OrderDO orderDO = orderService.selectOrder2(1);
        log.info("[查询结果为{}]" , orderDO);
    }

    @Test
    public void testSelectOrderRandomDS() {
        OrderDO orderDO = orderService.selectOrderRandomDS(1);
        log.info("[查询结果为{}]" , orderDO);
    }

}
