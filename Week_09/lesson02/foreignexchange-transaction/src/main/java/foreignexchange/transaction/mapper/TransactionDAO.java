package foreignexchange.transaction.mapper;

import foreignexchange.transaction.entity.TransactionSerialDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lihongjian
 * @since 2021/3/20
 */
@Repository
public interface TransactionDAO {

    @Insert("insert into t_transaction_serial(customer_userId,customer_balance,customer_account_type,boss_userId,boss_balance,boss_accountType,tx_id,status,create_time)" +
            "values(#{customerUserId},#{customerBalance},#{customerAccountType},#{bossUserId},#{bossBalance},#{bossAccountType},#{txId},#{status},#{createTime})")
    void save(TransactionSerialDO transactionSerialDO);

    @Update("update t_transaction_serial set status=#{status} where tx_id=#{txId}")
    void update(Long txId , Integer status);
}
