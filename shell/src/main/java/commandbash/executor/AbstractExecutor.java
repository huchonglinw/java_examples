package commandbash.executor;

import commandbash.Result;
import commandbash.command.Command;
import constant.Constant;
import constant.ErrorEnum;
import exception.CommandException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器默认实现
 *
 * @author: hcl
 * @date: 2021/6/7 14:48
 */
public abstract class AbstractExecutor implements Executor {
    @Override
    public Result process(Command command) throws CommandException, IOException {
        Result result = new Result();
        List<String> dataSources = command.getDataSources();

        check(command);

        if (dataSources.size() == 1 && command.getDataSourcesType().equals(Constant.FROM_FILE)) {
            command.setDataSources(readFile(dataSources));
        }
        if (command.getDataSourcesType().equals(Constant.FROM_PIPE)) {
            command.setDataSources(filterDataSource(command));
        }

        command.setDataSources(filterEnd(command));

        result.setResult(command.getDataSources());
        return result;
    }

    /**
     * 读文件
     *
     * @param dataSources
     * @return
     * @throws CommandException
     */
    private ArrayList<String> readFile(List<String> dataSources) throws CommandException, IOException {
        File file = new File(dataSources.get(0));
        FileReader fis = null;
        BufferedReader br = null;
        String str;
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            fis = new FileReader(file);
            br = new BufferedReader(fis);
            while ((str = br.readLine()) != null) {
                arrayList.add(readFileFilter(str));
            }
            fis.close();
            br.close();
        } catch (FileNotFoundException e) {
            throw new CommandException(ErrorEnum.FILE_NOT_EXIXT.getMsg());
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return arrayList;
    }

    /**
     * 条件检查
     *
     * @param command
     * @throws CommandException
     */
    protected void check(Command command) throws CommandException {
        if (command.getDataSources() == null) {
            throw new CommandException(ErrorEnum.COMMAND_NOT_EXIXT.getMsg());
        }
    }


    //====================================由子类实现===================================

    /**
     * 数据过滤 由子类重写
     */
    protected String readFileFilter(String str) {
        return str;
    }


    /**
     * 从管道中过滤数据
     *
     * @param command
     * @return
     */
    protected List<String> filterDataSource(Command command) {
        return command.getDataSources();
    }

    /**
     * 最终过滤
     *
     * @param command
     */
    protected List<String> filterEnd(Command command) {
        return command.getDataSources();
    }
}
