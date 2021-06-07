package constant;

/**
 * @author: hcl
 * @date: 2021/6/5 23:21
 */
public interface Constant {
    String COMMAND_LIST = "commandconfig.properties";

    String COMMON_COMMAND = "common_command";

    String COMPLEX_COMMAND = "complex_command";

    Integer MIN_LENGTH = 2;

    Integer MAX_LENGTH = 4;

    Integer FROM_FILE = 0;

    Integer FROM_PIPE = 1;

    String OPTION_PREFIX = "-";

    Integer TWO_LENGTH_COMMAND = 2;

    Integer THREE_LENGTH_COMMAND = 3;
}
