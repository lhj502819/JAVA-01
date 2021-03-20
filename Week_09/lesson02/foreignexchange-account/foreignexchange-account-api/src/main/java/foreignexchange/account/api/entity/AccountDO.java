package foreignexchange.account.api.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Data
public class AccountDO {
    /**
     * 账号ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 账户类型 1：USD 2:CHN
     */
    private Integer accountType;

    /**
     * 美元余额
     */
    private Long usdAmount;

    /**
     * 人民币余额
     */
    private Long chnAmount;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
