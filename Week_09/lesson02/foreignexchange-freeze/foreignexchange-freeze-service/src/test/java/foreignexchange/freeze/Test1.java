package foreignexchange.freeze;

import foreignexchange.freeze.api.constant.FrozenStatusEnum;
import foreignexchange.freeze.api.dto.AssetsFrozenDTO;
import foreignexchange.freeze.api.service.IAssetsFreezeService;
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
@SpringBootTest(classes = FreezeApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    private IAssetsFreezeService assetsFreezeService;

    @Test
    public void testSave(){
        AssetsFrozenDTO assetsFrozenDTO = AssetsFrozenDTO.builder()
                .userId(1)
                .txId(848484484484L)
                .accountType(1)
                .amount(45646546L)
                .status(FrozenStatusEnum.PREPARE.getValue()).build();
        assetsFreezeService.save(assetsFrozenDTO);
    }

    @Test
    public void testUpdate(){
        AssetsFrozenDTO assetsFrozenDTO = AssetsFrozenDTO.builder()
                .userId(1)
                .txId(848484484484L)
                .status(FrozenStatusEnum.COMMITTED.getValue()).build();
        assetsFreezeService.update(assetsFrozenDTO);
    }
}
