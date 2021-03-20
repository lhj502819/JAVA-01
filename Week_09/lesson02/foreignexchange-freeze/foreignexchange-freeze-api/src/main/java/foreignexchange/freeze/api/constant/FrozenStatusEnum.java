package foreignexchange.freeze.api.constant;

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
public enum FrozenStatusEnum {
    PREPARE(1, "准备完成"),
    COMMITTED(2, "已提交"),
    INVALID(3, "失效");

    private Integer value;

    private String name;
}
