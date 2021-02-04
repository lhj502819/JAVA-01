package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method06 {

    public static Integer result = 0;

    public static void main(String[] args) throws Exception{
        WorkerThread5 workerThread = new WorkerThread5();
        workerThread.setThread(Thread.currentThread());
        workerThread.start();
        LockSupport.park();
        if(Thread.currentThread().isInterrupted()){
            System.out.println("获取到结果：" + result);
        }
    }
}

class WorkerThread5 extends Thread {

    public Thread thread  = null;


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
        Method06.result = sum();
        thread.interrupt();
        LockSupport.unpark(thread);
    }

}