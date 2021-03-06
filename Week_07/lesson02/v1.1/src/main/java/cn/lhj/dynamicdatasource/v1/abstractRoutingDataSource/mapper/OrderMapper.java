package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.mapper;

import cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper
 *
 * @author lihongjian
 * @since 2021/3/6
 */
public interface OrderMapper {

    OrderDO selectById(@Param("orderId") Integer orderId);

    Integer updateById(@Param("entity") OrderDO orderDO);
}
