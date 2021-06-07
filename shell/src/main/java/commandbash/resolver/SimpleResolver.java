package commandbash.resolver;

import commandbash.command.Command;
import constant.Constant;
import constant.ErrorEnum;
import exception.CommandException;

import java.util.ArrayList;

/**
 * 普通命令解析器
 * @author: hcl
 * @date: 2021/6/6 00:20
 */
public class SimpleResolver {
    public Command resolve(String inputCommand) throws CommandException {
        String[] commands = inputCommand.trim().split(" ");
        Command command;
        if(commands.length == Constant.TWO_LENGTH_COMMAND) {
            command = twoLengthResolve(commands);
        } else if(commands.length == Constant.THREE_LENGTH_COMMAND) {
            command = threeLengthResolve(commands);
        } else {
            throw new CommandException(ErrorEnum.COMMAND_NOT_EXIXT.getMsg());
        }
        return command;
    }

    /**
     * 三个参数解析器
     * cat -n fileName
     * @param commands
     * @return
     */
    private Command threeLengthResolve(String[] commands) {
        Command command = new Command();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(commands[2]);

        command.setCommandName(commands[0]);
        command.setOption(commands[1]);
        command.setDataSources(arrayList);
        command.setDataSourcesType(Constant.FROM_FILE);
        return command;
    }

    /**
     * 两个参数解析器
     * cat fileName
     * | cat -n
     * @param commands
     * @return
     */
    private Command twoLengthResolve(String[] commands) {
        Command command = new Command();
        command.setCommandName(commands[0]);
        String opt = commands[1];
        if(opt.startsWith(Constant.OPTION_PREFIX)) {
            command.setOption(opt);
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(opt);
            command.setDataSources(arrayList);
            command.setDataSourcesType(Constant.FROM_FILE);
        }
        return command;
    }

    /**
     * 解析参数
     * @param commands
     */
    private void resolveOpt(String[] commands) {

    }
}
