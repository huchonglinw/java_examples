import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import task.Callable1;

import java.util.concurrent.Executors;

/**
 * @author: hcl
 * @date: 2021/7/17 22:21
 */
@Slf4j
public class ListenableFutureTaskDemo {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public void test() {
        ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable1());
        service.submit(task);

        task.addListener(() -> log.info("catch!"), service);

    }
    public static void main(String[] args) {

    }
}
