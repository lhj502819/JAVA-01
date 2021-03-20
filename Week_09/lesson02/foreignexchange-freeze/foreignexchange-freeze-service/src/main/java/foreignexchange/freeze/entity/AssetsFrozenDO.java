package foreignexchange.freeze.entity;

import foreignexchange.freeze.api.constant.FrozenStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 冻结表DO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Builder
@Data
public class AssetsFrozenDO {

    /**
     * 账号ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 交易ID
     */
    private Long txId;

    /**
     * 账户类型 1：USD 2:CHN
     */
    private Integer accountType;

    /**
     * 余额
     */
    private Long amount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 冻结状态
     * {@link FrozenStatusEnum}
     */
    private Integer status;


}
