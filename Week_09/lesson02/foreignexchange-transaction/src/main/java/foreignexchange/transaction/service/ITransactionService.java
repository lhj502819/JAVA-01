package foreignexchange.transaction.service;

import foreignexchange.transaction.dto.TransactionDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
public interface ITransactionService {

    @Hmily
    public void makeTransaction(TransactionDTO transactionDTO);

}
