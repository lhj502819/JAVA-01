package foreignexchange.freeze.mapper;

import foreignexchange.freeze.api.dto.AssetsFrozenDTO;
import foreignexchange.freeze.entity.AssetsFrozenDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
@Repository
public interface AssetsFreezeDAO {

    @Insert("insert into t_assets_frozen(tx_id,user_id,account_type,amount,status,create_time) " +
            "values(#{txId},#{userId},#{accountType},#{amount},#{status},#{createTime})")
    void save(AssetsFrozenDO assetsFrozenDO);

    @Update("update t_assets_frozen set status=#{status},update_time=now() " +
            "where tx_id=#{txId} and user_id=#{userId}" )
    void update(AssetsFrozenDTO assetsFrozenDTO);

}
