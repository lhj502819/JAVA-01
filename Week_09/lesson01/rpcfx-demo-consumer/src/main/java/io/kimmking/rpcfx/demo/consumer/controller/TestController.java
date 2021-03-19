package io.kimmking.rpcfx.demo.consumer.controller;

import io.kimmking.rpcfx.annotation.Reference;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/18
 */
@RestController
public class TestController {
    @Reference(url = "http://localhost:8081/")
    private UserService userService;

    @Reference(url = "http://localhost:8081/")
    private OrderService orderService;

    @RequestMapping("/test")
    public void test() {
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
    }
}
