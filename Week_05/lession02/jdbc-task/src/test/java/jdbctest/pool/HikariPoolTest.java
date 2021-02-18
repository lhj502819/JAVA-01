package jdbctest.pool;

import jdbctest.HikariPoolConfig;
import jdbctest.JDBCUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Description：{@link com.zaxxer.hikari.pool.HikariPool}连接池
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/18
 */
public class HikariPoolTest {

    Connection con = null;

    @Before
    public void prepare() throws Exception{
        con = HikariPoolConfig.getConnection();
    }

    @Test
    public void insert() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "insert into t_base_mail (mail_account) values('lhj502819@163.com')";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(con , statement , null);
        }
    }

    @Test
    public void delete() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "delete from t_base_mail where id = 1";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(con , statement , null);
        }
    }

    @Test
    public void update() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "update t_base_mail set mail_account='new-mail' where id = 2";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(con , statement , null);
        }
    }

    @Test
    public void query() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "select mail_account from t_base_mail";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String mail_account = resultSet.getString("mail_account");
                System.out.println(mail_account);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(con , statement , null);
        }
    }

}
