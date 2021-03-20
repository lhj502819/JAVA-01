package foreignexchange.account.userB.service;

import foreignexchange.account.api.constant.AccountTypeEnum;
import foreignexchange.account.api.constant.OperateTypeEnum;
import foreignexchange.account.api.dto.AccountDTO;
import foreignexchange.account.api.dto.OperateAmount;
import foreignexchange.account.api.service.IAccountService;
import foreignexchange.account.userB.mapper.AccountDAO;
import foreignexchange.freeze.api.constant.FrozenStatusEnum;
import foreignexchange.freeze.api.dto.AssetsFrozenDTO;
import foreignexchange.freeze.api.service.IAssetsFreezeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
@Slf4j
@Service(version = "1.0.0", group = "userB")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Reference(version = "1.0.0" , timeout = 60000)
    private IAssetsFreezeService iAssetsFreezeService;

    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "updateConfirm", cancelMethod = "updateCancel")
    @Override
    public Integer update(AccountDTO accountDTO) {
        try {
            OperateAmount operateAmount = accountDTO.getOperateMap().get(OperateTypeEnum.SUB.getType());
            if (operateAmount.getAccoutType().equals(AccountTypeEnum.USD.getType())) {
                //美元账户加钱
                accountDAO.updateSubUSD(accountDTO.getUserId() , operateAmount.getBalance());
            } else {
                //人民币账户加钱
                accountDAO.updateSubCHN(accountDTO.getUserId() , operateAmount.getBalance());
            }
            AssetsFrozenDTO assetsFrozenDTO = AssetsFrozenDTO.builder()
                    .userId(accountDTO.getUserId())
                    .txId(accountDTO.getTxId())
                    .accountType(operateAmount.getAccoutType())
                    .amount(operateAmount.getBalance())
                    .status(FrozenStatusEnum.PREPARE.getValue()).build();
            //修改冻结记录
            iAssetsFreezeService.save(assetsFrozenDTO);
        } catch (Exception e) {
            log.error("[AccountServiceImpl#addBalance][修改账户失败，入参({})]", accountDTO.toString(), e);
            throw new HmilyException("修改账户失败");
        }
        return 1;
    }

    private void updateAssetsFrozen(AccountDTO accountDTO, OperateAmount operateAmount , Integer status) {
        AssetsFrozenDTO assetsFrozenDTO = AssetsFrozenDTO.builder()
                .userId(accountDTO.getUserId())
                .txId(accountDTO.getTxId())
                .status(status).build();
        //修改冻结记录
        iAssetsFreezeService.update(assetsFrozenDTO);
    }
    @Transactional(rollbackFor = Exception.class)
    public void updateConfirm(AccountDTO accountDTO){
        try {
            OperateAmount operateAmount = accountDTO.getOperateMap().get(OperateTypeEnum.ADD.getType());
            if (operateAmount.getAccoutType().equals(AccountTypeEnum.USD.getType())) {
                //美元账户加钱
                accountDAO.updateAddUSD(accountDTO.getUserId() , operateAmount.getBalance());
            } else {
                //人民币账户加钱
                accountDAO.updateAddCHN(accountDTO.getUserId() , operateAmount.getBalance());
            }
            updateAssetsFrozen(accountDTO, operateAmount , FrozenStatusEnum.COMMITTED.getValue());
        } catch (Exception e) {
            log.error("[AccountServiceImpl#subBalance][修改账户失败，入参({})]", accountDTO.toString(), e);
            throw new HmilyException("修改账户失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCancel(AccountDTO accountDTO){
        OperateAmount operateAmount = accountDTO.getOperateMap().get(OperateTypeEnum.SUB.getType());
        if (operateAmount.getAccoutType().equals(AccountTypeEnum.USD.getType())) {
            //美元账户加钱
            accountDAO.updateAddUSD(accountDTO.getUserId() , operateAmount.getBalance());
        } else {
            //人民币账户加钱
            accountDAO.updateAddCHN(accountDTO.getUserId() , operateAmount.getBalance());
        }
        updateAssetsFrozen(accountDTO , operateAmount ,FrozenStatusEnum.INVALID.getValue());
    }
}
