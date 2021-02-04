package thread;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method03 {

    public static Integer result = 0;

    public static void main(String[] args) throws Exception {

        Object monitor = new Object();
        WorkerThread2 workerThread = new WorkerThread2();
        workerThread.setMonitor(monitor);
        workerThread.start();
        synchronized (workerThread) {
            workerThread.join();
        }

        System.out.println("获取到结果：" + result);

    }

}

class WorkerThread2 extends Thread {

    public Object monitor;

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
        synchronized (monitor) {
            Method03.result = sum();
            monitor.notifyAll();
        }
    }

    public void setMonitor(Object monitor) {
        this.monitor = monitor;
    }
}

