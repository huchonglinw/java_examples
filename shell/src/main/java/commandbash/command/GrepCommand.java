package commandbash.command;

/**
 * grep 命令结构体
 * @author: hcl
 * @date: 2021/6/5 01:27
 */
public class GrepCommand extends Command {
    /**
     * 正则表达式
     */
    private String patterns;

    public String getPatterns() {
        return patterns;
    }

    public void setPatterns(String patterns) {
        this.patterns = patterns;
    }
}
