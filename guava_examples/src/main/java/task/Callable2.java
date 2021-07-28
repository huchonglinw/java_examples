package task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 带有返回值的callable 任务
 * @author: hcl
 * @date: 2021/7/17 22:28
 */
@Slf4j
public class Callable2 implements Callable {
    @Override
    public Integer call() throws Exception {
        log.info("this is my second callable task!sleep 2000");
        Thread.sleep(20000000);
        return 2000;
    }
}
