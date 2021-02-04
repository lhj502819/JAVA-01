package thread;


/**
 * 通过wait - notify机制
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/3
 */
public class Method02 {

    public static Integer result = 0;

    public static void main(String[] args) throws Exception{

        Object monitor = new Object();
        WorkerThread workerThread = new WorkerThread();
        workerThread.setMonitor(monitor);
        Thread thread = new Thread(workerThread , "Worker-Thread");
        thread.start();
        synchronized (monitor){
            monitor.wait();
        }

        System.out.println("获取到结果：" + result);

    }

}

class WorkerThread implements Runnable{

    public Object monitor;

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    @Override
    public void run() {
        synchronized (monitor) {
            Method02.result = sum();
            monitor.notifyAll();
        }
    }

    public void setMonitor(Object monitor) {
        this.monitor = monitor;
    }
}
