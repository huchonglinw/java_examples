package commandbash.executor.adaptor;

import commandbash.Result;

/**
 * bash 和 executor执行器进行解耦
 * @author: hcl
 * @date: 2021/7/13 0:07
 */
public interface ExecutorAdaptor {

    /**
     * 对外提供获取执行器适配器的方法
     * @param input
     * @return
     */
    ExecutorAdaptor getExecutorAdaptor(String input);

    /**
     * 核心逻辑
     * @return
     */
    Result process();
}
