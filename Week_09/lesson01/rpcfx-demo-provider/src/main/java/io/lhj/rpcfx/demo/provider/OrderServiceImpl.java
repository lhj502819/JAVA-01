package io.lhj.rpcfx.demo.provider;

import io.lhj.rpcfx.demo.api.Order;
import io.lhj.rpcfx.demo.api.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
