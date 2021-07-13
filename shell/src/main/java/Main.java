import commandbash.BashContext;
import exception.CommandException;

/**
 * 测试命令：
 * 1、cat D:\asd.txt
 * 2、cat D:\asd.txt | grep 1
 * grep 1 D:\asd.txt
 * cat D:\asd.txt | grep 1 D:\asd.txt
 * 3、 cat D:\asd.txt | grep 1 | wc -l
 * wc -l D:\秋招心得.txt
 * @author: hcl
 * @date: 2021/6/5 19:38
 */
public class Main {
    public static void main(String[] args) throws  CommandException {
        BashContext bashContext = chooseBash();
        load(bashContext);
    }

    /**
     * 仿照Linux bash，用于选择哪个bash启动程序，如处理命令行的bash、用户登录的bash...
     */
    private static BashContext chooseBash() {
        return new BashContext();
    }

    /**
     * 初始化bash
     * 1. 初始化单例类（ioc）、命令控制台
     * 2. 执行主逻辑
     * @param bashContext
     */
    private static void load(BashContext bashContext) throws CommandException {
        bashContext.loadBash();
        bashContext.process();
    }
}
