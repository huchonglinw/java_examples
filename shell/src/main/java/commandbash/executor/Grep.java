package commandbash.executor;

import commandbash.command.Command;
import commandbash.command.PatternCommand;
import constant.ErrorEnum;
import exception.CommandException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * grep [options] pattern [filename]
 * @author: hcl
 * @date: 2021/6/6 15:58
 */
public class Grep extends AbstractFileCommandExecutor {
    @Override
    protected void check(Command command) throws CommandException {
        super.check(command);
        PatternCommand patternCommand = (PatternCommand)command;
        String patterns = patternCommand.getPatterns();
        if(patterns == null || patterns.length() == 0) {
            throw new CommandException(ErrorEnum.COMMAND_OPT_ERROR.getMsg());
        }
    }

    @Override
    protected List<String> filterEnd(Command command) {
        PatternCommand patternCommand = (PatternCommand)command;
        return command.getDataSources().stream().filter(str -> str.contains(patternCommand.getPatterns())).collect(Collectors.toList());

    }
}
