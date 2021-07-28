package thread.serialThread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: hcl
 * @date: 2021/7/22 23:43
 */
public class SerialThreadContext {
    //    private static final Integer CPUS;

    private Integer estimatedTaskNumber;

    public SerialThreadContext() {}

    public SerialThreadContext(Integer estimatedTaskNumber) {
        this.estimatedTaskNumber = estimatedTaskNumber;
    }

    {
        this.estimatedTaskNumber = 1000;
        //        CPUS = Runtime.getRuntime().availableProcessors();
    }

    // 如果拒绝策略让用户线程去做，则可能出问题
    // 此处涉及一个非常小的问题，就是初始化顺序问题，先static、再init，都是从上往下执行，最后执行构造
    // 如果这个变量放在上边，则出错。
    private final ListeningExecutorService executorService
            = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(1,
            1,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(estimatedTaskNumber)));

    public void submit(Integer times, Collection<Runnable> runnables) {
        loadRunnable(times,runnables);
    }

    public void submit(Integer times, Runnable... runnables) {
        loadRunnable(times, Arrays.asList(runnables));
    }

    private void loadRunnable(Integer times, Collection<Runnable> runnables) {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(runnables);

        for (int i = 0; i < times; i++) {
            for (Runnable runnable : linkedBlockingQueue) {
                executorService.submit(runnable);
            }
        }
    }
}
