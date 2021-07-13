package commandbash.executor;

import commandbash.Result;
import commandbash.command.Command;
import exception.CommandException;

import java.io.IOException;

/**
 * 命令行为规范
 * @author: hcl
 * @date: 2021/6/5 00:48
 */
public interface CommandExecutor {
    /**
     * 命令执行
     * @param command 命令结构体
     */
    Result process(Command command) throws CommandException, IOException;

}
