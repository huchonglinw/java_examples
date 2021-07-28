import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import task.Callable2;
import task.FutureCallBack1;
import task.Callable1;
import task.FutureCallBack2;

import java.util.concurrent.Executors;

/**
 * @author: hcl
 * @date: 2021/7/17 22:42
 */
public class FuturesDemo {
    // 返回的其实是实现类 ListeningDecorator
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public void addCallback() throws InterruptedException {
        // 调用submit之后，会将callable封装成RunnableFuture（TrustedListenableFutureTask是其子类）
        // 返回的是TrustedListenableFutureTask。value为返回值
        // 那么是如何把返回值绑定在future上的呢？
        ListenableFuture future1 = service.submit(new Callable1());
        ListenableFuture future2 = service.submit(new Callable2());

//        ListenableFuture listenableFuture = Futures.successfulAsList(future1, future2);
        Futures.addCallback(future1, new FutureCallBack1(), service);
        Futures.addCallback(future2, new FutureCallBack2(), service);

//        Futures.addCallback(listenableFuture, new CallBackTask(), service);

    }

    public void test1() {
        ListenableFuture future1 = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am a runnable task!");
            }
        });
//        ListenableFuture future2 = service.submit(new CallableTaskSecond());

//        ListenableFuture listenableFuture = Futures.successfulAsList(future1, future2);
        Futures.addCallback(future1, new FutureCallBack1(), service);
    }

    public void successfulListDemo() {
        ListenableFuture future1 = service.submit(new Callable1());
        ListenableFuture future2 = service.submit(new Callable2());

        Futures.addCallback(future1, new FutureCallBack1(), service);
        Futures.addCallback(future2, new FutureCallBack2(), service);
        ListenableFuture listenableFuture = Futures.successfulAsList(future1, future2);
        Futures.addCallback(listenableFuture,new FutureCallBack2(),service);

    }
    public static void main(String[] args) throws InterruptedException {
        FuturesDemo futuresDemo = new FuturesDemo();
        futuresDemo.successfulListDemo();
    }
}
