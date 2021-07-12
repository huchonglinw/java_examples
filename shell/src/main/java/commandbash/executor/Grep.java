package commandbash.executor;

import commandbash.command.Command;
import commandbash.command.GrepCommand;
import constant.ErrorEnum;
import exception.CommandException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * grep [options] pattern [filename]
 * @author: hcl
 * @date: 2021/6/6 15:58
 */
public class Grep extends AbstractFileExecutor {
    @Override
    protected void check(Command command) throws CommandException {
        super.check(command);
        GrepCommand grepCommand = (GrepCommand)command;
        String patterns = grepCommand.getPatterns();
        if(patterns == null || patterns.length() == 0) {
            throw new CommandException(ErrorEnum.COMMAND_OPT_ERROR.getMsg());
        }
    }

    @Override
    protected List<String> filterEnd(Command command) {
        GrepCommand grepCommand = (GrepCommand)command;
        return command.getDataSources().stream().filter(str -> str.contains(grepCommand.getPatterns())).collect(Collectors.toList());

    }
}
