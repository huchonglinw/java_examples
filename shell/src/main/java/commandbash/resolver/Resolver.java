package commandbash.resolver;

import commandbash.command.Command;
import exception.CommandException;

/**
 * 命令解析器，用于解析不同规范的命令。
 * 何为不同的规范？command [options] filename 与 command [pattern] [options] filename即为不同规范
 * @author: hcl
 * @date: 2021/6/6 00:17
 */
public interface Resolver {
    /**
     * 解析一条命令
     * @param command 字符串命令
     * @return
     */
    Command resolve(String command) throws CommandException;
}
