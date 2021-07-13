package commandbash.executor.adaptor;

import commandbash.BashContext;
import commandbash.Result;
import exception.CommandException;

import java.io.IOException;

/**
 * ExecutorAdaptor：用于bash 和 CommandExecutor命令执行器进行解耦，启到承上启下的作为
 * @author: hcl
 * @date: 2021/7/13 0:07
 * @see commandbash.executor.CommandExecutor
 * @see BashContext
 */
public interface ExecutorAdaptor {

    /**
     * 执行方法
     * @return
     * @param input
     */
    Result execute(String input) throws CommandException, IOException;

    /**
     * 检查命令是否符合规范
     * @param input
     * @return
     */
    boolean checkCommand(String input) throws CommandException;
}
