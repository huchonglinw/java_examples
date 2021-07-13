package commandbash.executor;

import commandbash.command.Command;

import java.util.List;

/**
 * 统计数据源中的数据
 * @author: hcl
 * @date: 2021/6/7 14:48
 */
public class Wc extends AbstractFileCommandExecutor {
    private static final String OPTION_C = "-c";
    private static final String OPTION_L = "-l";
    private static final String OPTION_W = "-w";

    /**
     * todo 逻辑有误
     * @param command
     * @return
     */
    @Override
    protected List<String> filterEnd(Command command) {
        List<String> dataSources = command.getDataSources();
        if (dataSources == null) {
            return null;
        }

        String option = command.getOption();
        Integer line = dataSources.size();
        Integer word = 0;
        Integer byteCount = 0;

        for (String dataSource : dataSources) {
            word += dataSource.split(" ").length;
            byteCount += dataSource.length();
        }
        dataSources.clear();

        switch (option) {
            case OPTION_C:
                dataSources.add(byteCount.toString());
                break;
            case OPTION_L:
                dataSources.add(line.toString());
                break;
            case OPTION_W:
                dataSources.add(word.toString());
                break;
            default: {
                dataSources.add(line.toString());
                dataSources.add(word.toString());
                dataSources.add(byteCount.toString());
                break;
            }
        }
        return dataSources;
    }
}
