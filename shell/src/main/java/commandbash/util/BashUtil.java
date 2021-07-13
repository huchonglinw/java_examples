package commandbash.util;

/**
 * 工具类
 * @author: hcl
 * @date: 2021/7/13 23:09
 */
public class BashUtil {
    /**
     * 判断是否包含管道命令
     *
     * @param command 命令
     * @return 包含管道时返回true
     */
    public static boolean containsPipe(String command) {
        return command.contains("|");
    }
}
