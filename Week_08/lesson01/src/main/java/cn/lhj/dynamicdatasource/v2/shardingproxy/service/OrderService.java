package cn.lhj.dynamicdatasource.v2.shardingproxy.service;

import cn.lhj.dynamicdatasource.v2.shardingproxy.entity.OrderDO;
import cn.lhj.dynamicdatasource.v2.shardingproxy.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public OrderDO selectOrder(Long orderId) {
        return orderMapper.selectById(orderId);
    }


    /**
     * 测试插入
     * @return
     */
    public Integer insertOrder(OrderDO orderDO) {
        return orderMapper.insertOrder(orderDO);
    }

    /**
     * 测试查询所有
     * @return
     */
    public List<OrderDO> selectAll() {

        return orderMapper.selectAll();

    }
}
