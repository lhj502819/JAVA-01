package foreignexchange.account.api.dto;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.account.api.constant.OperateTypeEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class AccountDTO  implements Serializable {

    private static final long serialVersionUID = 5517221348457200892L;
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * key 操作类型 {@link OperateTypeEnum}
     * value 金额
     */
    private Map<Integer, OperateAmount> operateMap;

    /**
     * 交易ID
     */
    private Long txId;

}
