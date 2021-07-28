package task;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * @author: hcl
 * @date: 2021/7/20 10:23
 */
public class FutureCallbackDemo {

    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));
        ListenableFuture submit = listeningExecutorService.submit(new Callable1());
//        submit.addListener(new Callab);
    }
}
