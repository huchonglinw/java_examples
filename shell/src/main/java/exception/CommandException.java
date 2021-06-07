package exception;

/**
 * 命令异常
 * @author: hcl
 * @date: 2021/6/5 23:09
 */
public class CommandException extends Exception {
    private String errorMessage;

    public CommandException(String msg) {
        this.errorMessage = msg;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
