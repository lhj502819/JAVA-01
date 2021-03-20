package foreignexchange.account.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 交易金额类
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OperateAmount  implements Serializable {
    private static final long serialVersionUID = 1608645399568301856L;
    Integer operate;

    /**
     * {@link foreignexchange.account.api.constant.AccountTypeEnum}
     */
    Integer accoutType;

    Long balance;
}
