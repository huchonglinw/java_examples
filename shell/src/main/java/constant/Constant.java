package constant;

/**
 * @author: hcl
 * @date: 2021/6/5 23:21
 */
public interface Constant {
    String COMMAND_LIST = "commandconfig.properties";

    /**
     * 普通命令的标识符（key）
     */
    String COMMON_COMMAND = "common_command";

    /**
     * 正则命令的标识符（key）
     */
    String PATTERN_COMMAND = "pattern_command";

    /**
     * 命令最小长度
     */
    Integer MIN_LENGTH = 2;

    /**
     * 命令最大长度
     * 截止2021/7/12，当前项目暂且实现了cat、grep、wc功能。因此限定一个最大长度为4
     */
    Integer MAX_LENGTH = 4;

    //===================数据源标识，表示数据源的来向===================

    /**
     * 来自文件
     */
    Integer FROM_FILE = 0;

    /**
     * 来自管道
     */
    Integer FROM_PIPE = 1;

    /**
     * 命令分隔符
     */
    String OPTION_PREFIX = "-";

    //===================因为程序只能获取字符串，只能根据长度判断命令 符合程序规范的 语义===================
    //===================例如，cat -x，解析器会将-x解析成文件，这样才能符合程序规范的语义===================
    /**
     * 两长度的命令
     */
    Integer TWO_LENGTH_COMMAND = 2;

    /**
     * 三长度的命令
     */
    Integer THREE_LENGTH_COMMAND = 3;
}
