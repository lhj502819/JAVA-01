package foreignexchange.account.api.service;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.account.api.constant.OperateTypeEnum;
import foreignexchange.account.api.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * 账户Service Interface
 *
 * @author lihongjian
 * @since 2021/3/20
 */
public interface IAccountService {

    /**
     * 加钱
     * @return
     */
    @Hmily
    Integer update(AccountDTO accountDTO);

}
