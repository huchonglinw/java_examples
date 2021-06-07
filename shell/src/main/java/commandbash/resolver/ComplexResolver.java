package commandbash.resolver;

import commandbash.command.GrepCommand;
import constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 复杂命令解析器
 * @author: hcl
 * @date: 2021/6/6 00:20
 */
public class ComplexResolver {
    public GrepCommand resolve(String inputCommand) {
        GrepCommand command = new GrepCommand();
        String[] commands = inputCommand.trim().split(" ");
        command.setCommandName(commands[0]);

        for (int i = 1; i < commands.length; i++) {
            String cur = commands[i];
            if(cur.startsWith(Constant.OPTION_PREFIX)) {
                command.setOption(cur);
            } else if(command.getPatterns() == null) {
                command.setPatterns(cur);
            } else if(command.getDataSources() == null) {
                List<String> arrayList = new ArrayList<>();
                arrayList.add(cur);
                command.setDataSources(arrayList);
                command.setDataSourcesType(Constant.FROM_FILE);
            }
        }
        return command;
    }
}
