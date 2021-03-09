package cn.lhj.dynamicdatasource.v2.shardingproxy;

import cn.lhj.dynamicdatasource.v2.shardingproxy.entity.OrderDO;
import cn.lhj.dynamicdatasource.v2.shardingproxy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

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
        OrderDO orderDOUpdate = orderService.updateOrder(OrderDO.builder().productName("huawei-Meta40").id(576150254819717120L).build());
        log.info("[修改结果为{}]" , orderDOUpdate);
    }

    @Test
    public void testSelectOrder() {
        OrderDO orderDO = orderService.selectOrder(576150254819717120L);
        log.info("[查询结果为{}]" , orderDO);
    }

    @Test
    public void testInsertOrder() {
        OrderDO orderDO = OrderDO.builder().orderNo("N82134412").buyPrice(999999)
                .deleted(0)
                .originPrice(8888888)
                .deliveryTime(LocalDateTime.now())
                .productName("iphone-18")
                .paymentTime(LocalDateTime.now())
                .productId(156464L)
                .productImage("http:xxxxxxx")
                .quantity(89989)
                .receiverTime(LocalDateTime.now())
                .status(0)
                .userId(89778978L).build();
        orderService.insertOrder(orderDO);
    }

    @Test
    public void testSelectAll() {
        List<OrderDO> orderDOs = orderService.selectAll();
        orderDOs.stream().forEach(orderDO -> log.info("[查询结果为{} \n]" , orderDO));
        log.info("[查询结果为{}]" , orderDOs);
    }



}
