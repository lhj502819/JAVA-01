package foreignexchange.transaction.entity;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.account.api.constant.OperateTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 交易流水DO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Builder
@Data
public class TransactionSerialDO {
    private Integer id;

    /**
     * 购买人ID
     */
    private Integer customerUserId;

    /**
     * 购买人花费金额
     */
    private Long customerBalance;

    /**
     * 购买人交易账户类型
     */
    private Integer customerAccountType;

    /**
     * 购买人ID
     */
    private Integer bossUserId;

    /**
     * 购买人花费金额
     */
    private Long bossBalance;

    /**
     * 购买人交易账户类型
     */
    private Integer bossAccountType;

    private Long txId;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
