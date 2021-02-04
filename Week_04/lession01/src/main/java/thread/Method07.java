package thread;

import java.util.concurrent.Semaphore;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method07 {

    /**
     * 利用Semaphore来阻塞主线程
     */
    public static final Semaphore semaphore = new Semaphore(1);

    public static Integer result = 0;

    public static void main(String[] args) throws Exception{
        WorkerThread6 workerThread = new WorkerThread6();
        workerThread.setSemaphore(semaphore);
        workerThread.start();
        Thread.sleep(100);
        semaphore.acquire();
        System.out.println("获取到结果：" + result);
        semaphore.release();
    }

}

class WorkerThread6 extends Thread {

    public Semaphore semaphore  = null;


    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
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
            semaphore.acquire();
            Method07.result = sum();
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
