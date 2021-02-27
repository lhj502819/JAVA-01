package jdbctest.tx;

import jdbc.Application;
import jdbctest.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Description：PreparedStatement JDBC
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JdbcTest {

    private static  Connection con = null;

    private static PreparedStatement statement = null;


    @Before
    public void prepare() throws Exception{
        con = JDBCUtil.getConnection();
    }

    @After
    public void post(){
        JDBCUtil.closeResource(con , statement , null);
    }

    @Test
    public void insert() {
        //通过工具类获取数据库连接对象
        try {
            String sql = "insert into t_base_mail (mail_account) values(?)";
            statement = con.prepareStatement(sql);
            statement.setString(1 , "lhj502819@163.com");
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            String sql = "delete from t_base_mail where id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1,3);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            String sql = "update t_base_mail set mail_account='new-mail' where id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1,2);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void query() {
        try {
            String sql = "select mail_account from t_base_mail";
            statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String mail_account = resultSet.getString("mail_account");
                System.out.println(mail_account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
