package util.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 测试CompletableFuture基本操作
 * @see java.util.concurrent.CompletableFuture
 * @author: hcl
 * @date: 2021/7/18 0:19
 */
public class CompletableFuturesDemo {
    private static final Logger log = LoggerFactory.getLogger(CompletableFuturesDemo.class);

    @Test
    public void test() throws InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            log.info("this is a one task");
            return 1;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            log.info("this is a two task");

            // 模拟异常
//            int i = 1/0;

            return 1;
        });
//        completableFuture2.cancel(true);

        completableFuture2.thenAccept((r -> {
            log.info("the result is {}", r);
        }));
        completableFuture2.exceptionally((e -> {
            log.warn("error message is {}", e.getMessage());
            return -1;
        }));

        CompletableFuture<List<Integer>> listCompletableFuture = CompletableFutures.successfulAsList(completableFuture1, completableFuture2);
        listCompletableFuture.thenAccept(e -> {
            log.info("this is the successfulAsList：{}", e);
        });
        Thread.sleep(2000);

    }

    @Test
    public void runSyncTest() {
        CompletableFuture.runAsync(() -> System.out.println("hello")).join();
    }
}
