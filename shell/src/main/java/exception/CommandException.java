package exception;

/**
 * 命令异常结构体
 * @author: hcl
 * @date: 2021/6/5 23:09
 */
public class CommandException extends Exception {
    /**
     * 命令的错误信息
     */
    private String errorMessage;

    public CommandException(String msg) {
        this.errorMessage = msg;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
