package cn.lhj.dynamicdatasource.v2.shardingproxy.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 订单实体类
 *
 * @author lihongjian
 * @since 2021/3/6
 */
@Builder
@ToString
@Data
public class OrderDO {

    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 原始价格，单位：分
     */
    private Integer originPrice;

    /**
     * 购买价格，单位：分
     */
    private Integer buyPrice;

    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 收货时间
     */
    private LocalDateTime receiverTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态：0、代发货 1、已发货 2、已收货 20、换货中 21、换货成功 40、退货中 41、已退货
     */
    private Integer status;

    /**
     * 删除状态 0：未删除  1：已删除
     */
    private Integer deleted;

}
