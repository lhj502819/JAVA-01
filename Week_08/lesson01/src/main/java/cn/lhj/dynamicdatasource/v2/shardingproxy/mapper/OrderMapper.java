package cn.lhj.dynamicdatasource.v2.shardingproxy.mapper;

import cn.lhj.dynamicdatasource.v2.shardingproxy.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper
 *
 * @author lihongjian
 * @since 2021/3/6
 */
public interface OrderMapper {

    OrderDO selectById(@Param("orderId") Long orderId);

    Integer updateById(@Param("entity") OrderDO orderDO);

    Integer insertOrder(@Param("entity") OrderDO orderDO);

    List<OrderDO> selectAll();
}
