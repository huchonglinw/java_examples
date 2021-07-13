package commandbash.executor.adaptor;

import commandbash.BashContext;
import commandbash.Result;
import commandbash.command.Command;
import commandbash.executor.CommandExecutor;
import commandbash.resolver.Resolver;
import constant.Constant;
import constant.ErrorEnum;
import exception.CommandException;

import java.io.IOException;

/**
 * 只有一个命令的执行适配器
 * @author: hcl
 * @date: 2021/7/13 23:16
 */
public class OneCommandExecutorAdaptor implements ExecutorAdaptor{
    private BashContext bashContext;

    public OneCommandExecutorAdaptor(BashContext bashContext) {
        this.bashContext = bashContext;
    }

    @Override
    public Result execute(String input) throws CommandException, IOException {
        if(checkCommand(input)) {
            Resolver resolver = bashContext.getResolver(input);
            if(resolver == null) {
                throw new CommandException(ErrorEnum.COMMAND_OPT_ERROR.getMsg());
            }
            Command command = resolver.resolve(input);
            CommandExecutor executor = bashContext.getExecutor(command);
            return executor.process(command);
        }
        return null;
    }

    @Override
    public boolean checkCommand(String input) throws CommandException {
        if (input == null || input.length() == 0) {
            throw new CommandException(ErrorEnum.COMMAND_ERROR.getMsg());
        }

        String[] commands = input.trim().split("\\s");
        int commandsLength = commands.length;
        if (commandsLength < Constant.MIN_LENGTH || commandsLength > Constant.MAX_LENGTH) {
            throw new CommandException(ErrorEnum.COMMAND_NOT_EXIXT.getMsg());
        }

        return true;
    }
}
