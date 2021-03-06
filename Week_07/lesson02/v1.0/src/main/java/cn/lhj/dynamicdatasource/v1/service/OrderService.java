package cn.lhj.dynamicdatasource.v1.service;

import cn.lhj.dynamicdatasource.v1.entity.OrderDO;
import cn.lhj.dynamicdatasource.v1.mapper.master.OrderMasterMapper;
import cn.lhj.dynamicdatasource.v1.mapper.slave.OrderSlaveMapper;
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
    private OrderMasterMapper orderMasterMapper;

    @Resource
    private OrderSlaveMapper orderSlaveMapper;

    @Transactional
    public OrderDO updateOrder(OrderDO orderDO) {
       orderMasterMapper.updateById(orderDO);

       return orderMasterMapper.selectById(orderDO.getId());
    }

    public OrderDO selectOrder(Integer orderId) {
        return orderSlaveMapper.selectById(orderId);
    }

}
