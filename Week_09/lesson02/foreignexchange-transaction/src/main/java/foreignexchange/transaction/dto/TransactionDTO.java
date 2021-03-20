package foreignexchange.transaction.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
@Builder
@Data
public class TransactionDTO {
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

}
