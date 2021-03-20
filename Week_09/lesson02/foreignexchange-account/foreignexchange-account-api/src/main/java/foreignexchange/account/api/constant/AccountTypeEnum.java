package foreignexchange.account.api.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资产类型枚举
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@AllArgsConstructor
@Getter
public enum AccountTypeEnum {
    USD(1, "美元"),
    CHN(2, "人民币");

    private Integer type;

    private String name;
}
