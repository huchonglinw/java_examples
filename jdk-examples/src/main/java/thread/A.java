package thread;

/**
 * @author: chonglin.hu
 * @date: 2021/9/16 12:54
 */
public class A extends Common{

    public synchronized void join1() {
        wait1();
    }
}
