package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method05 {

    public static final Lock lock = new ReentrantLock();

    public static Integer result = 0;

    public static void main(String[] args) {
        WorkerThread4 workerThread = new WorkerThread4();
        workerThread.setLock(lock);
        workerThread.start();
        try {
            //此处睡1秒，让workerThread先拿到锁
            Thread.sleep(1000);
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.lock();
        }
        System.out.println("获取到结果：" + result);
    }
}


class WorkerThread4 extends Thread {

    public Lock lock  = null;


    public void setLock(Lock lock) {
        this.lock = lock;
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
            lock.lock();
            Method05.result = sum();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}