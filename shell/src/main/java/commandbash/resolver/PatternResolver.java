package commandbash.resolver;

import commandbash.command.Command;
import commandbash.command.PatternCommand;
import constant.Constant;
import exception.CommandException;

import java.util.ArrayList;
import java.util.List;

/**
 * 正则命令解析器
 *
 * @author: hcl
 * @date: 2021/6/6 00:20
 */
public class PatternResolver implements Resolver {

    @Override
    public Command resolve(String inputCommand) throws CommandException {
        PatternCommand patternCommand = new PatternCommand();
        String[] commands = inputCommand.trim().split(" ");
        patternCommand.setCommandName(commands[0]);

        for (int i = 1; i < commands.length; i++) {
            String cur = commands[i];
            if (cur.startsWith(Constant.OPTION_PREFIX)) {
                patternCommand.setOption(cur);
            } else if (patternCommand.getPatterns() == null) {
                patternCommand.setPatterns(cur);
            } else if (patternCommand.getDataSources() == null) {
                List<String> arrayList = new ArrayList<>();
                arrayList.add(cur);
                patternCommand.setDataSources(arrayList);
                patternCommand.setDataSourcesType(Constant.FROM_FILE);
            }
        }
        return patternCommand;
    }
}
