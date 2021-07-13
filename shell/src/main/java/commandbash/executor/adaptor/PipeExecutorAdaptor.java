package commandbash.executor.adaptor;

import commandbash.BashContext;
import commandbash.Result;
import commandbash.command.Command;
import commandbash.command.Pipe;
import commandbash.executor.CommandExecutor;
import commandbash.resolver.Resolver;
import constant.Constant;
import constant.ErrorEnum;
import exception.CommandException;

import java.io.IOException;

/**
 * 管道执行适配器：用于bash 和 CommandExecutor命令执行器进行解耦，启到承上启下的作为。
 * <p>
 * 包含管道的处理方法{@link PipeExecutorAdaptor#pipeAdaptor(Command, Pipe)}
 *
 * 1. 声明一块缓冲区
 * 2. 第一个命令执行完之后，将结果集设置到缓冲区（内存中）。
 * 3. 第二个命令在执行之前，将数据来源从缓冲区中读取，并设置来源格式
 *
 * 例子：cat [-] filename | grep [-x] pattern [filename]
 *
 * @author: hcl
 * @date: 2021/7/13 23:13
 */
public class PipeExecutorAdaptor implements ExecutorAdaptor {
    private BashContext bashContext;

    public PipeExecutorAdaptor(BashContext bashContext) {
        this.bashContext = bashContext;
    }

    @Override
    public Result execute(String input) throws CommandException, IOException {
        Result result = null;
        String[] commands = input.split("\\|");
        Pipe pipe = new Pipe();

        for (String commandStr : commands) {
            if (checkCommand(commandStr)) {
                Resolver resolver = bashContext.getResolver(commandStr);
                Command realCommand = resolver.resolve(commandStr);

                CommandExecutor executor = bashContext.getExecutor(realCommand);

                pipeAdaptor(realCommand, pipe);

                result = executor.process(realCommand);
                pipe.setBuffer(result.getResult());
            }

        }
        return result;
    }

    /**
     * 管道适配方法
     * @param realCommand
     * @param pipe
     */
    private void pipeAdaptor(Command realCommand, Pipe pipe) {
        if (realCommand.getDataSources() == null) {
            realCommand.setDataSources(pipe.getBuffer());
            realCommand.setDataSourcesType(Constant.FROM_PIPE);
        }
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
