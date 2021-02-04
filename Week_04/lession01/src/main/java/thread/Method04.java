package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 通过 {@link LockSupport#park()} {@link LockSupport#unpark(Thread)}
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method04 {
    public static Integer result = 0;

    public static void main(String[] args) throws Exception {
        WorkerThread3 workerThread = new WorkerThread3();
        workerThread.setThread(Thread.currentThread());
        workerThread.start();
        LockSupport.park();
        System.out.println("获取到结果：" + result);
    }

}


class WorkerThread3 extends Thread {

    public Thread thread = null;

    public void setThread(Thread thread) {
        this.thread = thread;
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
        Method04.result = sum();
        LockSupport.unpark(thread);
    }

}
