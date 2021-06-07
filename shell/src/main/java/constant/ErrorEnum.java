package constant;

/**
 * @author: hcl
 * @date: 2021/6/5 23:16
 */
public enum ErrorEnum {
    COMMAND_NOT_EXIXT("命令不存在"),
    COMMAND_ERROR("命令错误"),
    FILE_NOT_EXIXT("文件不存在"),
    COMMAND_OPT_ERROR("命令参数错误");

    private String msg;

    ErrorEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
