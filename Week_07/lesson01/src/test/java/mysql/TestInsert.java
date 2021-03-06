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
import java.util.concurrent.atomic.AtomicReference;

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

    Timestamp timestamp;

    @Before
    public void before() {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zone).toInstant();
        timestamp = new Timestamp(instant.toEpochMilli());
    }

    @Test
    public void insert() throws SQLException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            int finalJ = j;
            Connection connection = null;
            connection = hikariDataSource.getConnection();
            Connection finalConnection = connection;
            String sql = "insert into t_order (order_no,product_id," +
                    "product_name,product_image," +
                    "quantity,origin_price," +
                    "buy_price,payment_time," +
                    "delivery_time,receiver_time," +
                    "user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
            AtomicReference<PreparedStatement> statement = new AtomicReference<>(connection.prepareStatement(sql));
            executorService.submit(() -> {
                PreparedStatement preparedStatement = statement.get();
                for (int i = 0; i < 200000; i++) {
                    int finalI = i;
                    try {

                        preparedStatement.setString(1, "ON" + finalJ + finalI);
                        preparedStatement.setLong(2, 1L);
                        preparedStatement.setString(3, "iphone12 Pro Max");
                        preparedStatement.setString(4, "https://pgdt.gtimg.cn/gdt/0/EAAoQE3AGQAEsAAAJ0sBgPK30BQ2L_KWG.jpg/0?ck=b378ee8716b1e76ea1631e6359bd4d90&md5=b378ee8716b1e76ea1631e6359bd4d90");
                        preparedStatement.setInt(5, 555);
                        preparedStatement.setInt(6, 555);
                        preparedStatement.setInt(7, 555);
                        preparedStatement.setTimestamp(8, timestamp);
                        preparedStatement.setTimestamp(9, timestamp);
                        preparedStatement.setTimestamp(10, timestamp);
                        preparedStatement.setLong(11, 9999L);
                        preparedStatement.addBatch();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                try {
                    preparedStatement.executeBatch();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        finalConnection.close();
                        countDownLatch.countDown();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        executorService.shutdownNow();

    }

    @Test
    public void insert2() throws SQLException {
        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 1000000; j++) {
            Connection connection = null;
            try {
                connection = hikariDataSource.getConnection();
                String sql = "insert into t_order (order_no,product_id," +
                        "product_name,product_image," +
                        "quantity,origin_price," +
                        "buy_price,payment_time," +
                        "delivery_time,receiver_time," +
                        "user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "ON" + j);
                preparedStatement.setLong(2, 1L);
                preparedStatement.setString(3, "iphone12 Pro Max");
                preparedStatement.setString(4, "https://pgdt.gtimg.cn/gdt/0/EAAoQE3AGQAEsAAAJ0sBgPK30BQ2L_KWG.jpg/0?ck=b378ee8716b1e76ea1631e6359bd4d90&md5=b378ee8716b1e76ea1631e6359bd4d90");
                preparedStatement.setInt(5, 555);
                preparedStatement.setInt(6, 555);
                preparedStatement.setInt(7, 555);
                preparedStatement.setTimestamp(8, timestamp);
                preparedStatement.setTimestamp(9, timestamp);
                preparedStatement.setTimestamp(10, timestamp);
                preparedStatement.setLong(11, 9999L);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            } finally {
                connection.close();
                countDownLatch.countDown();
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void insert3() throws SQLException {
        long startTime = System.currentTimeMillis();
        Connection connection = null;
        connection = hikariDataSource.getConnection();
        String sql = "insert into t_order (order_no,product_id," +
                "product_name,product_image," +
                "quantity,origin_price," +
                "buy_price,payment_time," +
                "delivery_time,receiver_time," +
                "user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int j = 0; j < 1000000; j++) {
            try {

                preparedStatement.setString(1, "ON" + j);
                preparedStatement.setLong(2, 1L);
                preparedStatement.setString(3, "iphone12 Pro Max");
                preparedStatement.setString(4, "https://pgdt.gtimg.cn/gdt/0/EAAoQE3AGQAEsAAAJ0sBgPK30BQ2L_KWG.jpg/0?ck=b378ee8716b1e76ea1631e6359bd4d90&md5=b378ee8716b1e76ea1631e6359bd4d90");
                preparedStatement.setInt(5, 555);
                preparedStatement.setInt(6, 555);
                preparedStatement.setInt(7, 555);
                preparedStatement.setTimestamp(8, timestamp);
                preparedStatement.setTimestamp(9, timestamp);
                preparedStatement.setTimestamp(10, timestamp);
                preparedStatement.setLong(11, 9999L);
                preparedStatement.addBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            } finally {
                connection.close();
            }
        }

        preparedStatement.executeBatch();

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
}
