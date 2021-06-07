package commandbash;

import commandbash.command.Command;
import commandbash.executor.Cat;
import commandbash.executor.Executor;
import commandbash.executor.Grep;
import commandbash.command.Pipe;
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
     * 命令容器池
     */
    private static Map<String, Executor> commandsMap = new HashMap<>(255);

    private SimpleResolver simpleResolver = new SimpleResolver();

    private ComplexResolver complexResolver = new ComplexResolver();

    public void initBash() throws IOException {
        Properties properties = new Properties();
        properties.load(Bash.class.getClassLoader().getResourceAsStream(Constant.COMMAND_LIST));
        simpleCommandsList = Arrays.asList(properties.getProperty(Constant.COMMON_COMMAND).split(","));
        complexCommandsList = Arrays.asList(properties.getProperty(Constant.COMPLEX_COMMAND).split(","));

        /**
         * 后期可以转成ioc单例模式
         */
        commandsMap.put("cat", new Cat());
        commandsMap.put("grep", new Grep());
        commandsMap.put("wc",new Wc());

    }

    /**
     * 核心处理命令逻辑：调用解析器解析命令，得到命令结构体。此后，调用对应的命令执行器
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
            firstParse(input);

            try {
                Executor executor;
                Result result = null;
                if (containsPipe(input)) {
                    String[] commands = splitCommandsByPipe(input);
                    Pipe pipe = new Pipe();

                    for (String command : commands) {
                        Command commandDefinition;

                        commandDefinition = parseCommand(command);
                        executor = getExecutor(commandDefinition);

                        if (commandDefinition.getDataSources() == null) {
                            commandDefinition.setDataSources(pipe.getBuffer());
                            commandDefinition.setDataSourcesType(Constant.FROM_PIPE);
                        }

                        result = executor.process(commandDefinition);
                        pipe.setBuffer(result.getResult());
                    }
                } else {
                    Command command = parseCommand(input);
                    executor = getExecutor(command);
                    result = executor.process(command);
                }
                print(result);
            } catch (CommandException e) {
                System.err.println(e.getErrorMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
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
        result.getResult().forEach(System.out::println);
        System.out.println("命令执行结束");
    }

    /**
     * 根据命令结构体获取执行器
     *
     * @param commandDefinition
     * @return
     */
    private Executor getExecutor(Command commandDefinition) {
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
     * 解析字符串命令 封装成GlobalCommand
     * 首先，判断命令以空格分隔后的个数是否符合标准。如果命令的个数符合标准，则取字符的第一个单词，
     * 判断普通命令和复杂命令（依据该字符是否包含在静态集合中），调用不同的命令解析器进行解析。
     * 命令解析器会将字符串命令封装成命令结构体。
     * <p>
     * 一个命令以空格分隔后的字符串个数应该在2-4之间
     *
     * @param inputCommand 命令字符串
     * @return 命令结构体
     * @see Command
     */
    private Command parseCommand(String inputCommand) throws CommandException {
        if (inputCommand == null || inputCommand.length() == 0) {
            throw new CommandException(ErrorEnum.COMMAND_ERROR.getMsg());
        }
        String[] commands = inputCommand.trim().split(" ");
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
     * 按管道分割命令
     *
     * @param command
     * @return 命令字符串数组
     */
    private static String[] splitCommandsByPipe(String command) {
        return command.split("\\|");
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
