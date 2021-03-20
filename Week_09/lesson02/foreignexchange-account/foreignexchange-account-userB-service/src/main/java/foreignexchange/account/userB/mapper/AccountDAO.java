package foreignexchange.account.userB.mapper;

import foreignexchange.account.api.dto.AccountDTO;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
@Repository
public interface AccountDAO {

    @Update("update t_account set usd_amount = usd_amount + #{balance},update_time=now() where user_id = #{userId}")
    int updateAddUSD(Integer userId , Long balance);

    @Update("update t_account set usd_amount = usd_amount - #{balance},update_time=now() where user_id = #{userId}")
    int updateSubUSD(Integer userId , Long balance);

    @Update("update t_account set chn_amount = chn_amount + #{balance},update_time=now() where user_id = #{userId}")
    int updateAddCHN(Integer userId , Long balance);

    @Update("update t_account set chn_amount = chn_amount - #{balance},update_time=now() where user_id = #{userId}")
    int updateSubCHN(Integer userId , Long balance);

}
