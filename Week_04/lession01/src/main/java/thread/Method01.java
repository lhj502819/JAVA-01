package thread;


/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/3
 */
public class Method01 {

    private static Integer result = null;


    public static void main(String[] args) {
        //开启一个线程去执行
        new Thread(() -> {
            result = sum();
        } , "Sum-Thread").start();
        //main线程阻塞等待
        while (result == null){
            assert result == null;
        }
        System.out.println("获取到结果：" + result);
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
