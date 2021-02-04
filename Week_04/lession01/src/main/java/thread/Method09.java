package thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method09 {

    /**
     * 利用CyclicBarrier来阻塞主线程
     */
    public static Integer result = 0;

    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new WorkerThread8(cyclicBarrier));
        cyclicBarrier.await();
        System.out.println("获取到结果：" + result);
        executorService.shutdown();
    }

}

class WorkerThread8 implements Runnable {

    CyclicBarrier cyclicBarrier ;

    public WorkerThread8(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
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
            Method09.result = sum();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

}
