package concurrent;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: hcl
 * @date: 2021/7/23 18:00
 */
public class MoreExecutorsDemo {
    public static void main(String[] args) {
        MoreExecutors.getExitingExecutorService((ThreadPoolExecutor) Executors.newSingleThreadExecutor());
    }
}
