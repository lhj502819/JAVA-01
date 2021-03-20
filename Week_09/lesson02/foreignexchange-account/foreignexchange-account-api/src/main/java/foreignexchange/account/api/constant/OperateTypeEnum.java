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
public enum OperateTypeEnum {
    ADD(1, "加钱操作"),
    SUB(2, "扣钱操作");

    private Integer type;

    private String name;
}
