package foreignexchange.freeze.service;

import foreignexchange.freeze.api.dto.AssetsFrozenDTO;
import foreignexchange.freeze.api.service.IAssetsFreezeService;
import foreignexchange.freeze.entity.AssetsFrozenDO;
import foreignexchange.freeze.mapper.AssetsFreezeDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author lihongjian
 * @since 2021/3/20
 */

@Slf4j
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class AssetsAssetsFreezeServiceImpl implements IAssetsFreezeService {


    @Autowired
    private AssetsFreezeDAO assetsFreezeDAO;

    @Override
    public Integer save(AssetsFrozenDTO assetsFrozenDTO) {
        AssetsFrozenDO assetsFrozenDO = AssetsFrozenDO.builder().amount(assetsFrozenDTO.getAmount())
                .accountType(assetsFrozenDTO.getAccountType())
                .status(assetsFrozenDTO.getStatus())
                .txId(assetsFrozenDTO.getTxId())
                .userId(assetsFrozenDTO.getUserId())
                .createTime(LocalDateTime.now()).build();
        try {
            assetsFreezeDAO.save(assetsFrozenDO);
        } catch (Exception e) {
            log.error("保存资产冻结表异常" , e);
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public Integer update(AssetsFrozenDTO assetsFrozenDTO) {
        try {
            assetsFreezeDAO.update(assetsFrozenDTO);
        } catch (Exception e) {
            log.error("更新资产冻结表异常" , e);
            throw new RuntimeException();
        }
        return 1;
    }
}
