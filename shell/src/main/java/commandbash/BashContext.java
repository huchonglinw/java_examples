package commandbash;

import commandbash.command.Command;
import commandbash.executor.Cat;
import commandbash.executor.CommandExecutor;
import commandbash.executor.Grep;
import commandbash.executor.Wc;
import commandbash.executor.adaptor.ExecutorAdaptor;
import commandbash.executor.adaptor.OneCommandExecutorAdaptor;
import commandbash.executor.adaptor.PipeExecutorAdaptor;
import commandbash.resolver.PatternResolver;
import commandbash.resolver.Resolver;
import commandbash.resolver.CommonResolver;
import commandbash.util.BashUtil;
import constant.Constant;
import constant.ErrorEnum;
import exception.CommandException;

import java.io.IOException;
import java.util.*;

/**
 * 命令控制台
 *
 * @author: hcl
 * @date: 2021/6/5 23:51
 */
public class BashContext {
    /**
     * 普通命令集合
     */
    private static List<String> commonCommandsList = new LinkedList<>();

    /**
     * 正则命令集合
     */
    private static List<String> patternCommandsList = new LinkedList<>();

    /**
     * 命令容器池（仿ioc，初始化255个命令容量）
     */
    private static Map<String, CommandExecutor> commandsMap = new HashMap<>(255);

    /**
     * 命令与解析器映射
     */
    private static Map<String, Resolver> commandResolverMap = new HashMap<>(10);

    /**
     * 初始化bash程序
     * 1. 初始化命令名字集合
     * 2. 初始化命令程序集合
     *
     * @throws CommandException
     */
    public void loadBash() throws CommandException {
        loadCommandList();
        loadIoc();
    }

    /**
     * 初始化命令的ioc容器
     * 后期完善：实例化并放在ioc中
     * 完善目的：单例模式用于提高程序性能
     */
    private void loadIoc() {
        commandsMap.put("cat", new Cat());
        commandsMap.put("grep", new Grep());
        commandsMap.put("wc", new Wc());

        commandResolverMap.put(Constant.COMMON_COMMAND, new CommonResolver());
        commandResolverMap.put(Constant.PATTERN_COMMAND, new PatternResolver());
    }

    /**
     * 从配置文件中获取命令名字
     */
    private void loadCommandList() throws CommandException {
        Properties properties = new Properties();
        try {
            properties.load(BashContext.class.getClassLoader().getResourceAsStream(Constant.COMMAND_LIST));
        } catch (IOException e) {
            throw new CommandException(ErrorEnum.CONFIG_NOT_FOUND.getMsg());
        }
        commonCommandsList = Arrays.asList(properties.getProperty(Constant.COMMON_COMMAND).split(","));
        patternCommandsList = Arrays.asList(properties.getProperty(Constant.PATTERN_COMMAND).split(","));
    }

    /**
     * 核心处理命令逻辑
     * 1. 首先会获取用户输入input
     * 2. 根据input命令不同的类型，调用不同的executorAdaptor。例如管道类型的命令会调用PipeAdaptor，会有一段通用逻辑
     *
     * 调用解析器解析命令，得到命令结构体。此后，调用对应的命令执行器
     *
     * 1. 命令只从数据来源（有可能是文件也有可能是管道）中获取数据
     * 2. 管道前面的命令需要将结果集设置到管道中
     */
    public void process() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            Result result = null;

            try {
                ExecutorAdaptor executorAdaptor = getExecutorAdaptor(input);
                result = executorAdaptor.execute(input);
            } catch (CommandException e) {
                System.err.println(e.getErrorMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            print(result);
        }
    }

    /**
     * 获取ExecutorAdaptor
     *
     * @param input
     */
    private ExecutorAdaptor getExecutorAdaptor(String input) {
        if (BashUtil.containsPipe(input)) {
            return new PipeExecutorAdaptor(this);
        } else {
            return new OneCommandExecutorAdaptor(this);
        }
    }

    /**
     * 根据命令结构体获取执行器
     *
     * @param commandDefinition
     * @return
     */
    public CommandExecutor getExecutor(Command commandDefinition) {
        return commandsMap.get(commandDefinition.getCommandName());
    }

    /**
     * 根据命令名称获得命令解析器
     * @param command
     * @return
     */
    public Resolver getResolver(String command) {
        String commandName = command.trim().split("\\s")[0];
        if(commonCommandsList.contains(commandName)) {
            return commandResolverMap.get(Constant.COMMON_COMMAND);
        } else if(patternCommandsList.contains(commandName)) {
            return commandResolverMap.get(Constant.PATTERN_COMMAND);
        }

        return null;
    }


    /**
     * 输出结果
     *
     * @param result
     */
    public void print(Result result) {
        if (result == null) {
            return;
        }
        System.out.println(result.getResult());
    }
}
