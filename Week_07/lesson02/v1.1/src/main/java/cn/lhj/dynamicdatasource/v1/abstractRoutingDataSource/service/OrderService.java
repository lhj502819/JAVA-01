package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.service;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.annotation.DS;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.aspect.OperationEnum;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.constant.DBConstant;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.entity.OrderDO;
import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.mapper.OrderMapper;
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
    @DS(value = DBConstant.DATASOURCE_MASTER , operate = OperationEnum.WRITE)
    public OrderDO updateOrder(OrderDO orderDO) {
       orderMapper.updateById(orderDO);
       return orderMapper.selectById(orderDO.getId());
    }

    /**
     * 测试读写分离
     * @param orderId
     * @return
     */
    @DS(value = DBConstant.DATASOURCE_SLAVE1 , operate = OperationEnum.READ)
    public OrderDO selectOrder(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

    /**
     * 多个从库
     * @param orderId
     * @return
     */
    @DS(value = DBConstant.DATASOURCE_SLAVE2 , operate = OperationEnum.READ)
    public OrderDO selectOrder2(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

    /**
     * 动态路由多个从库
     * @param orderId
     * @return
     */
    @DS(operate = OperationEnum.READ )
    public OrderDO selectOrderRandomDS(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

}
