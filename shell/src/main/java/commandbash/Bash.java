package commandbash;

import commandbash.command.Command;
import commandbash.command.Pipe;
import commandbash.executor.Cat;
import commandbash.executor.Executor;
import commandbash.executor.Grep;
import commandbash.executor.Wc;
import commandbash.resolver.ComplexResolver;
import commandbash.resolver.SimpleResolver;
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
public class Bash {
    /**
     * 普通命令集合
     */
    private static List<String> simpleCommandsList = new LinkedList<>();

    /**
     * 复杂命令集合
     */
    private static List<String> complexCommandsList = new LinkedList<>();

    /**
     * 命令容器池（仿ioc，初始化255个命令容量）
     */
    private static Map<String, Executor> commandsMap = new HashMap<>(255);

    private SimpleResolver simpleResolver = new SimpleResolver();

    private ComplexResolver complexResolver = new ComplexResolver();

    /**
     * 初始化bash程序
     * 1. 初始化命令名字集合
     * 2. 初始化命令程序集合
     *
     * @throws CommandException
     */
    public void initBash() throws CommandException {
        getCommandsName();
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
//        for (String commandName : simpleCommandsList) {
//
//        }
    }

    /**
     * 从配置文件中获取命令名字
     */
    private void getCommandsName() throws CommandException {
        Properties properties = new Properties();
        try {
            properties.load(Bash.class.getClassLoader().getResourceAsStream(Constant.COMMAND_LIST));
        } catch (IOException e) {
            throw new CommandException(ErrorEnum.CONFIG_NOT_FOUND.getMsg());
        }
        simpleCommandsList = Arrays.asList(properties.getProperty(Constant.COMMON_COMMAND).split(","));
        complexCommandsList = Arrays.asList(properties.getProperty(Constant.COMPLEX_COMMAND).split(","));
    }

    /**
     * 核心处理命令逻辑
     * 1. 首先会获取用户输入input
     * 2. 根据input命令不同的类型，调用不同的executorAdaptor
     *
     * 调用解析器解析命令，得到命令结构体。此后，调用对应的命令执行器
     * 包含管道的处理方法：声明一块缓冲区
     * 执行完第一个命令之后，将结果集设置到缓冲区（内存中）。第二个命令在执行之前，将数据来源从缓冲区中读取，并设置来源格式
     * cat [-] filename | grep [-x] pattern [filename]
     * 1. 命令只从数据来源（有可能是文件也有可能是管道）中获取数据
     * 2. 管道前面的命令需要将结果集设置到管道中
     */
    public void process() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
//            firstParse(input);
            Result result = null;

            getExecutorAdaptor(input);
            try {
                if (containsPipe(input)) {
                    result = processWithPipe(input);
                } else {
                    result = defaultProcess(input);
                }
            } catch (CommandException e) {
                System.err.println(e.getErrorMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            print(result);
        }
    }

    /**
     * todo 需要抽出一个context上下文，用于关联Bash和 ExecutorAdaptor
     * @param input
     */
    private void getExecutorAdaptor(String input) {

    }

    private Result defaultProcess(String input) throws CommandException, IOException {
        Command command = parseCommand(input);
        Executor executor = getExecutor(command);
        return executor.process(command);
    }

    /**
     * 管道执行器
     * 1. 按管道划分成多个命令
     * 2. 调用解析命令方法
     * 3. 获取到命令对应的执行器
     * @param input 用户输入
     * @return 结果集的封装
     */
    private Result processWithPipe(String input) throws CommandException, IOException {
        Result result = null;
        String[] commands = input.split("\\|");
        Pipe pipe = new Pipe();

        for (String command : commands) {
            Command commandDefinition = parseCommand(command);
            Executor executor = getExecutor(commandDefinition);
            if (commandDefinition.getDataSources() == null) {
                commandDefinition.setDataSources(pipe.getBuffer());
                commandDefinition.setDataSourcesType(Constant.FROM_PIPE);
            }
            result = executor.process(commandDefinition);
            pipe.setBuffer(result.getResult());
        }
        return result;
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

    /**
     * 根据命令结构体获取执行器
     *
     * @param commandDefinition
     * @return
     */
    public Executor getExecutor(Command commandDefinition) {
        return commandsMap.get(commandDefinition.getCommandName());
    }

    /**
     * 命令初解析
     * 判断符号优先级以划分：例如”>“，”|“
     *
     * @param input
     */
    private void firstParse(String input) {

    }

    /**
     * 解析字符串命令，并封装成Command命令结构体。
     *
     * 首先，判断命令是否符合规范。如果命令的个数符合标准，则取字符的第一个单词。
     *
     * 判断命令类型：属于普通命令和复杂命令（依据该字符是否包含在静态集合中），调用不同的命令解析器进行解析。
     * 命令解析器会将字符串命令封装成命令结构体。
     *
     * @param inputCommand 命令字符串
     * @return 命令结构体
     * @see Command 命令结构体
     */
    private Command parseCommand(String inputCommand) throws CommandException {
        if (inputCommand == null || inputCommand.length() == 0) {
            throw new CommandException(ErrorEnum.COMMAND_ERROR.getMsg());
        }
        String[] commands = inputCommand.trim().split("\\s");
        int commandsLength = commands.length;
        if (commandsLength < Constant.MIN_LENGTH || commandsLength > Constant.MAX_LENGTH) {
            throw new CommandException(ErrorEnum.COMMAND_NOT_EXIXT.getMsg());
        }

        String commandName = commands[0];
        Command command;
        if (simpleCommandsList.contains(commandName)) {
            command = simpleResolver.resolve(inputCommand);
        } else if (complexCommandsList.contains(commandName)) {
            command = complexResolver.resolve(inputCommand);
        } else {
            throw new CommandException(ErrorEnum.COMMAND_NOT_EXIXT.getMsg());
        }
        return command;
    }

    /**
     * 判断是否包含管道命令
     *
     * @param command 命令
     * @return 包含管道时返回true
     */
    private static boolean containsPipe(String command) {
        return command.contains("|");
    }
}
