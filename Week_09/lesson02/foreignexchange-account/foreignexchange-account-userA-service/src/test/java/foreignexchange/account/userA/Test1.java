package foreignexchange.account.userA;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.account.api.constant.OperateTypeEnum;
import foreignexchange.account.api.dto.AccountDTO;
import foreignexchange.account.api.dto.OperateAmount;
import foreignexchange.account.api.service.IAccountService;
import foreignexchange.freeze.api.constant.FrozenStatusEnum;
import foreignexchange.freeze.api.dto.AssetsFrozenDTO;
import foreignexchange.freeze.api.service.IAssetsFreezeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Slf4j
@SpringBootTest(classes = UserAAccountApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Qualifier("userAAccountService")
    @Autowired
    private IAccountService accountService;

    @Test
    public void testSave(){
        Map<Integer, OperateAmount> bossOperateMap = new HashMap<>(2);
        bossOperateMap.put(OperateTypeEnum.ADD.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.ADD.getType())
                .accoutType(AccountTypeEnum.USD.getType())
                .balance(1L).build());
        bossOperateMap.put(OperateTypeEnum.SUB.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.SUB.getType())
                .accoutType(AccountTypeEnum.CHN.getType())
                .balance(7l).build());
        AccountDTO bossrAccountDTO = AccountDTO.builder()
                .txId(5464647L)
                .userId(1)
                .operateMap(bossOperateMap).build();
        //修改UserB的账户
        accountService.update(bossrAccountDTO);
    }

}
