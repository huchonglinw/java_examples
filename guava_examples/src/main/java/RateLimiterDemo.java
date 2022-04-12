import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: hcl
 * @date: 2021/8/3 15:50
 */
public class RateLimiterDemo {
    public ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(5);
    public long time = System.currentTimeMillis();
    /**
     * 定义了500，acquired(2000)
     * hello!5054 令牌500，预分配直接获取，-1500，3秒后还完
     * hello!8055 令牌0，预分配直接获取，-2000，4秒后还完
     * hello!12055
     * hello!16056
     * hello!20056
     * 所以是一个预支的思想，先消费再偿还令牌（而不是等到令牌足够才执行）
     */
    public RateLimiter rateLimiter = RateLimiter.create(500);

    public static void main(String[] args) throws InterruptedException {
        RateLimiterDemo rateLimiterDemo = new RateLimiterDemo();
        Thread.sleep(5000);
        for (int i = 0; i < 5; i++) {
            rateLimiterDemo.threadPoolExecutor.submit(new MyRun(rateLimiterDemo.rateLimiter, rateLimiterDemo.time));
        }
    }

    static class MyRun implements Runnable {
        RateLimiter rateLimiter;
        long time;

        public MyRun(RateLimiter rateLimiter, long time) {
            this.rateLimiter = rateLimiter;
            this.time = time;
        }

        @Override
        public void run() {
            rateLimiter.acquire(2000);
            System.out.println("hello!" + (System.currentTimeMillis() - time));
        }
    }
}
