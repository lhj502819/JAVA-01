package foreignexchange.freeze.api.service;

import foreignexchange.freeze.api.dto.AssetsFrozenDTO;

/**
 * 冻结资产Service Interface
 *
 * @author lihongjian
 * @since 2021/3/20
 */
public interface IAssetsFreezeService {
    Integer save(AssetsFrozenDTO assetsFrozenDTO);

    Integer update(AssetsFrozenDTO assetsFrozenDTO);

}
