package foreignexchange.transaction.constant;

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
public enum TransactionStatusEnum {
    PREPARE(1, "准备"),
    COMPLET(2, "完成"),
    INVILID(2, "失效");

    private Integer value;

    private String name;
}
