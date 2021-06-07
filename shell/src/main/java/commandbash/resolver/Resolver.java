package commandbash.resolver;

import commandbash.command.Command;

/**
 * 命令解析器
 * @author: hcl
 * @date: 2021/6/6 00:17
 */
public interface Resolver {
    /**
     * 解析一条命令
     * @param command 字符串命令
     * @return
     */
    Command resolve(String command);
}
