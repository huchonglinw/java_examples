package task;

import com.google.common.util.concurrent.FutureCallback;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * 回调任务
 * @author: hcl
 * @date: 2021/7/17 22:30
 */
@Slf4j
public class FutureCallBack2 implements FutureCallback {
    @Override
    public void onSuccess(@Nullable Object result) {
      log.info("iam futureCallable2 now i catch a task!the result is {}",result);
    }

    @Override
    public void onFailure(Throwable t) {
        log.warn(t.getMessage());
    }
}
