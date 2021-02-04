package thread;

import java.util.concurrent.CountDownLatch;

/**
 * 利用CountDownLatch来阻塞主线程
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method08 {

    public static Integer result = 0;

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new WorkerThread7(countDownLatch)).start();
        countDownLatch.await();
        System.out.println("获取到结果：" + result);
    }

}

class WorkerThread7 implements Runnable {

    CountDownLatch countDownLatch ;

    public WorkerThread7(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    @Override
    public void run() {
        try {
            Method08.result = sum();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }

}
