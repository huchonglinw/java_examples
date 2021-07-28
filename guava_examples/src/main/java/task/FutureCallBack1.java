package task;

import com.google.common.util.concurrent.FutureCallback;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 回调任务
 * @author: hcl
 * @date: 2021/7/17 22:30
 */
public class FutureCallBack1 implements FutureCallback {
    private static final Logger log = LoggerFactory.getLogger(FutureCallBack1.class);

    @Override
    public void onSuccess(@Nullable Object result) {
      log.info("now i catch a task!the result is {}",result);
    }

    @Override
    public void onFailure(Throwable t) {
        log.warn(t.getMessage());
    }
}
