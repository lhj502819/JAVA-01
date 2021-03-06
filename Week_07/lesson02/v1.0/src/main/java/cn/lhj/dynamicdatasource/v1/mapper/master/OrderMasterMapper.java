package cn.lhj.dynamicdatasource.v1.mapper.master;

import cn.lhj.dynamicdatasource.v1.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper
 *
 * @author lihongjian
 * @since 2021/3/6
 */
public interface OrderMasterMapper {

    OrderDO selectById(@Param("orderId") Integer orderId);

    Integer updateById(@Param("entity") OrderDO orderDO);
}
