package foreignexchange.transaction.service;

import foreignexchange.account.api.constant.OperateTypeEnum;
import foreignexchange.account.api.dto.AccountDTO;
import foreignexchange.account.api.dto.OperateAmount;
import foreignexchange.account.api.service.IAccountService;
import foreignexchange.transaction.constant.TransactionStatusEnum;
import foreignexchange.transaction.dto.TransactionDTO;
import foreignexchange.transaction.entity.TransactionSerialDO;
import foreignexchange.transaction.mapper.TransactionDAO;
import foreignexchange.transaction.util.SnowflakeIdWorker;
import org.apache.dubbo.config.annotation.Reference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 交易Service
 *
 * @author lihongjian
 * @since 2021/3/20
 */
@Service
public class TransactionServiceImpl implements ITransactionService{

    @Reference(version = "1.0.0" , group = "userA" , timeout = 60000)
    public IAccountService userAAcountService;

    @Reference(version = "1.0.0" , group = "userB" , timeout = 60000)
    public IAccountService userBAcountService;

    @Autowired
    private TransactionDAO transactionDAO;

    @HmilyTCC(confirmMethod = "confirm" , cancelMethod = "cancel")
    @Override
    public void makeTransaction(TransactionDTO transactionDTO){
        Long txId = SnowflakeIdWorker.generateId();
        Map<Integer, OperateAmount>  customerOperateMap = new HashMap<>(2);
        //UserA作为购买人，设置UserA需要加/减的账户和钱
        customerOperateMap.put(OperateTypeEnum.ADD.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.ADD.getType())
                .accoutType(transactionDTO.getBossAccountType())
                .balance(transactionDTO.getBossBalance()).build());
        customerOperateMap.put(OperateTypeEnum.SUB.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.SUB.getType())
                .accoutType(transactionDTO.getCustomerAccountType())
                .balance(transactionDTO.getCustomerBalance()).build());
        AccountDTO customerAccountDTO = AccountDTO.builder()
                .txId(transactionDTO.getTxId())
                .userId(transactionDTO.getCustomerUserId())
                .operateMap(customerOperateMap).build();
        Map<Integer, OperateAmount>  bossOperateMap = new HashMap<>(2);
        //UserB作为被购买人，设置UserB需要加/减的账户和钱
        bossOperateMap.put(OperateTypeEnum.ADD.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.ADD.getType())
                .accoutType(transactionDTO.getCustomerAccountType())
                .balance(transactionDTO.getCustomerBalance()).build());
        bossOperateMap.put(OperateTypeEnum.SUB.getType(), OperateAmount.builder()
                .operate(OperateTypeEnum.SUB.getType())
                .accoutType(transactionDTO.getBossAccountType())
                .balance(transactionDTO.getBossBalance()).build());
        AccountDTO bossrAccountDTO = AccountDTO.builder()
                .txId(transactionDTO.getTxId())
                .userId(transactionDTO.getBossUserId())
                .operateMap(bossOperateMap).build();
        //修改UserB的账户
        userAAcountService.update(customerAccountDTO);
        //修改UserA的账户
        userBAcountService.update(bossrAccountDTO);
        //新增交易记录
        TransactionSerialDO customerTransactionSerialDO = TransactionSerialDO.builder()
                .customerUserId(transactionDTO.getCustomerUserId())
                .customerBalance(transactionDTO.getCustomerBalance())
                .customerAccountType(transactionDTO.getCustomerAccountType())
                .bossUserId(transactionDTO.getBossUserId())
                .bossBalance(transactionDTO.getBossBalance())
                .bossAccountType(transactionDTO.getBossAccountType())
                .txId(transactionDTO.getTxId())
                .status(TransactionStatusEnum.PREPARE.getValue())
                .createTime(LocalDateTime.now())
                .build();
        transactionDAO.save(customerTransactionSerialDO);
    }

    public void confirm(TransactionDTO transactionDTO){
        //修改交易状态为成功
        transactionDAO.update(transactionDTO.getTxId() , TransactionStatusEnum.COMPLET.getValue());
    }

    public void cancel(TransactionDTO transactionDTO){
        //修改交易状态为失败
        transactionDAO.update(transactionDTO.getTxId() , TransactionStatusEnum.INVILID.getValue());
    }








}
