package jdbctest;

import java.sql.*;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/14
 */
public class JDBCUtil {
    private static String drivername;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            drivername = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://172.17.15.62:3306/news_temp?useUnicode=true&characterEncoding=utf-8";
            user = "bohui";
            password = "Bohui@123";
            Class.forName(drivername);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
    }
    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
        return con;
    }
    /**
     * 关闭JDBC相关资源
     * @param con
     * @param sta
     * @param rs
     */
    public static void closeResource(Connection con, Statement sta, ResultSet rs) {
        try {
            if(con!=null) {
                con.close();
            }
            if(sta!=null) {
                sta.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
