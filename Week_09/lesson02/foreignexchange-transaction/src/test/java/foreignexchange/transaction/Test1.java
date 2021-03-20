package foreignexchange.transaction;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.transaction.dto.TransactionDTO;
import foreignexchange.transaction.service.ITransactionService;
import foreignexchange.transaction.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Slf4j
@SpringBootTest(classes = TransactionApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    private ITransactionService transactionService;

    @Test
    public void testSave() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .customerUserId(1)
                .customerBalance(7L)
                .customerAccountType(AccountTypeEnum.CHN.getType())
                .bossUserId(2)
                .bossAccountType(AccountTypeEnum.USD.getType())
                .bossBalance(1L)
                .txId(SnowflakeIdWorker.generateId()).build();

        transactionService.makeTransaction(transactionDTO);
    }

}
