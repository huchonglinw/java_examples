import commandbash.Bash;
import exception.CommandException;

/**
 * 测试命令：
 * 1、cat D:\秋招心得.txt
 * 2、cat D:\秋招心得.txt | grep 1
 * grep 1 D:\秋招心得.txt
 * cat D:\秋招心得.txt | grep 1 D:\秋招心得.txt
 * 3、 cat D:\秋招心得.txt | grep 1 | wc -l
 * wc -l D:\秋招心得.txt
 * @author: hcl
 * @date: 2021/6/5 19:38
 */
public class Main {
    public static void main(String[] args) throws  CommandException {
        Bash bash = chooseBash();
        init(bash);
    }

    /**
     * 仿照Linux bash，用于选择哪个bash启动程序，如处理命令行的bash、用户登录的bash...
     */
    private static Bash chooseBash() {
        return new Bash();
    }

    /**
     * 初始化bash
     * 1. 初始化单例类（ioc）、命令控制台
     * 2. 执行主逻辑
     * @param bash
     */
    private static void init(Bash bash) throws CommandException {
        bash.initBash();
        bash.process();
    }
}
