package util.concurrent;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * 可以修改permit的信号量
 *
 * @see java.util.concurrent.Semaphore
 * @author: hcl
 * @date: 2021/7/17 18:14
 */
public class DynamicSemaphore extends Semaphore {
    private static final Logger log = Logger.getLogger(DynamicSemaphore.class.getName());
    // 区别于初始化的令牌数量，count是用于内部统计的
    private int count;

    public DynamicSemaphore(int permits) {
        super(permits);
        count = permits;
    }

    public DynamicSemaphore(int permits, boolean fair) {
        super(permits, fair);
        count = permits;
    }

    /**
     * 调用release方法以增加令牌数量
     *
     * @param permits 令牌数量
     * @return 修改成功返回true
     */
    public void addPermits(int permits) {
        release(permits);
        count += permits;
    }

    /**
     * 通过目标令牌数量与现有的令牌数量作比较
     * 如果目标数量大于现有数量，则是增加令牌
     * 如果目标数量小于现有数量，则是减少令牌。但减少后的令牌必须大于0
     *
     * @param permits
     */
    public void updatePermits(int permits) throws InterruptedException {
        if (permits < 0) {
            throw new IllegalArgumentException();
        }

        int value = permits - count;
        if (value == 0) {
            return;
        }
        if (value > 0) {
            addPermits(value);
        } else {
            acquire(count - permits);
            count -= permits;
        }
    }

}
