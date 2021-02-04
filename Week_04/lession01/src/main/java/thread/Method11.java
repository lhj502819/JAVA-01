package thread;

import java.util.concurrent.*;

/**
 * 通过 {@link CompletableFuture} 异步阻塞获取执行结果
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/4
 */
public class Method11 {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return sum();
        });
        System.out.println("获取到结果：" + integerCompletableFuture.get());
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
