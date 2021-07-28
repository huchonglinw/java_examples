package thread;

import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huchonglin
 * @date 2020/12/24 18:54
 */
public class 测试线程池源码 extends BlockJUnit4ClassRunner {
    public 测试线程池源码(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Test
    public void test(){
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
    }

    @Test
    public void test2(){
//        new ThreadPoolExecutor(4,4,0,)
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {

        });
    }
}
