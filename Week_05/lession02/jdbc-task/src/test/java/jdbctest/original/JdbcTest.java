package jdbctest.original;

import jdbc.Application;
import jdbctest.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Description：原生JDBC
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JdbcTest {

    private static  Connection con = null;

    private static Statement statement = null;

    @Before
    public void prepare() throws Exception{
        con = JDBCUtil.getConnection();
        statement = con.createStatement();
    }

    @After
    public void post(){
        JDBCUtil.closeResource(con , statement , null);
    }


    @Test
    public void insert() {
        try {
            statement = con.createStatement();
            String sql = "insert into t_base_mail (mail_account) values('lhj502819@163.com')";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
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
