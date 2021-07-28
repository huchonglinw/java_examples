package util.concurrent;

import java.util.concurrent.*;

/**
 * @author: hcl
 * @date: 2021/7/18 18:11
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        // future作为顶层接口，返回的是其子类FutureTask，里面有个outCome属性，存放的是返回值
        Future<Integer> submit = executorService.submit(() -> {
            System.out.println(1);
            Thread.sleep(50000);
            return 200;
        });

        submit.get();
    }
}
