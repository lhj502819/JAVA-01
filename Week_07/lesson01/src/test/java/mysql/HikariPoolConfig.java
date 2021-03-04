package mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description：Hikari数据库连接池配置
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/18
 */
public class HikariPoolConfig {
    private static String drivername;
    private static String url;
    private static String user;
    private static String password;
    private static HikariDataSource dataSource ;


    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            drivername = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://rm-2zen1c7k0onnvs8zbyo.mysql.rds.aliyuncs.com:3306/t_order?useUnicode=true&characterEncoding=utf-8";
            user = "lhj502819";
            password = "960706Chh!";
            HikariConfig config = new HikariConfig();
            config.setMaximumPoolSize(50);
            config.setDriverClassName("com.mysql.jdbc.Driver");
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);
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
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean stop() throws SQLException {
        dataSource.close();
        return true;
    }


}
