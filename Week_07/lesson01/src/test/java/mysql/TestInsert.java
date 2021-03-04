package mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/3/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestInsert {

    @Autowired
    private HikariDataSource hikariDataSource;

    Timestamp timestamp ;

    @Before
    public void before() {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zone).toInstant();
        timestamp = new Timestamp(instant.toEpochMilli());
    }

    @Test
    public void insert() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        LocalTime startTime = LocalTime.now();
        for (int j = 0 ; j < 1000 ; j++) {
            int finalJ = j;
            executorService.submit(() -> {
                for (int i = 0 ; i < 1000 ; i++) {
                    int finalI = i;
                    Connection connection = null;
                    try {
                        connection = hikariDataSource.getConnection();
                        PreparedStatement statement = null;
                        String sql = "insert into t_order (order_no,product_id," +
                                "product_name,product_image," +
                                "quantity,origin_price," +
                                "buy_price,payment_time," +
                                "delivery_time,receiver_time," +
                                "user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1,"ON"  + finalJ + finalI);
                        statement.setLong(2 , 1L);
                        statement.setString(3,"iphone12 Pro Max");
                        statement.setString(4,"https://pgdt.gtimg.cn/gdt/0/EAAoQE3AGQAEsAAAJ0sBgPK30BQ2L_KWG.jpg/0?ck=b378ee8716b1e76ea1631e6359bd4d90&md5=b378ee8716b1e76ea1631e6359bd4d90");
                        statement.setInt(5 , 555);
                        statement.setInt(6 , 555);
                        statement.setInt(7 , 555);
                        statement.setTimestamp(8 , timestamp);
                        statement.setTimestamp(9 , timestamp);
                        statement.setTimestamp(10 , timestamp);
                        statement.setLong(11 , 9999L);
                        statement.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }finally {
                        try {
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("开始时间：" + startTime + " 结束时间" + LocalTime.now());


    }


    @Test
    public void test2() {
        for (int i = 0 ; i < 1000000 ; i++) {
            Connection connection = null;
            try {
                connection = hikariDataSource.getConnection();
                PreparedStatement statement = null;
                String sql = "insert into t_order (order_no,product_id," +
                        "product_name,product_image," +
                        "quantity,origin_price," +
                        "buy_price,payment_time," +
                        "delivery_time,receiver_time," +
                        "user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
                Random random = new Random(999999);
                ZoneId zone = ZoneId.systemDefault();
                Instant instant = LocalDateTime.now().atZone(zone).toInstant();
                statement = connection.prepareStatement(sql);
                statement.setString(1,"ON" + random.nextInt() + i);
                statement.setLong(2 , 1L);
                statement.setString(3,"iphone12 Pro Max");
                statement.setString(4,"https://pgdt.gtimg.cn/gdt/0/EAAoQE3AGQAEsAAAJ0sBgPK30BQ2L_KWG.jpg/0?ck=b378ee8716b1e76ea1631e6359bd4d90&md5=b378ee8716b1e76ea1631e6359bd4d90");
                statement.setInt(5 , random.nextInt());
                statement.setInt(6 , random.nextInt());
                statement.setInt(7 , random.nextInt());
                statement.setTimestamp(8 , new Timestamp(instant.toEpochMilli()));
                statement.setTimestamp(9 , new Timestamp(instant.toEpochMilli()));
                statement.setTimestamp(10 , new Timestamp(instant.toEpochMilli()));
                statement.setLong(11 , random.nextLong());
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

}
