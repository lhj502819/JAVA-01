package cn.lhj.dynamicdatasource.v2.shardingjdbc.service;

import cn.lhj.dynamicdatasource.v2.shardingjdbc.entity.OrderDO;
import cn.lhj.dynamicdatasource.v2.shardingjdbc.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lihongjian
 * @since 2021/3/6
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 测试读写分离
     * @param orderDO
     * @return
     */
    @Transactional
    public OrderDO updateOrder(OrderDO orderDO) {
       orderMapper.updateById(orderDO);
       return orderMapper.selectById(orderDO.getId());
    }

    /**
     * 测试读写分离
     * @param orderId
     * @return
     */
    public OrderDO selectOrder(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

    /**
     * 多个从库
     * @param orderId
     * @return
     */
    public OrderDO selectOrder2(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

    /**
     * 动态路由多个从库
     * @param orderId
     * @return
     */
    public OrderDO selectOrderRandomDS(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

}
