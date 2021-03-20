package foreignexchange.freeze.api.dto;

import foreignexchange.freeze.api.constant.FrozenStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AssetsFrozenDTO implements Serializable {


    private static final long serialVersionUID = 7837195608506895443L;
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
     * 冻结状态
     * {@link FrozenStatusEnum}
     */
    private Integer status;

}
