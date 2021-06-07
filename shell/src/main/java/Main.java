import commandbash.Bash;

import java.io.IOException;

/**
 * 测试命令：
 * 1、cat D:\秋招心得.txt
 * 2、cat D:\秋招心得.txt | grep 1
 * grep 1 D:\秋招心得.txt
 * cat D:\秋招心得.txt | grep 1 D:\秋招心得.txt
 * 3、 cat D:\秋招心得.txt | grep 1 | wc -l
 * @author: hcl
 * @date: 2021/6/5 19:38
 */
public class Main {
    public static void main(String[] args) throws IOException {
        chooseBash();
        init();
    }

    /**
     * 选择哪个bash启动程序：处理命令行、用户登录...
     */
    private static void chooseBash() {

    }

    /**
     * 初始化单例类（ioc）、命令控制台
     */
    private static void init() throws IOException {
        Bash bash = new Bash();
        bash.initBash();
        bash.process();
    }
}
