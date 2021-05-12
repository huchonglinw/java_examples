package OOM测试;

/**
 * @author huchonglin
 * @date 2021/3/29 10:23
 */

/**
 * -Xss128k
 * SOF总结：栈溢出，一般递归调用就会，因为栈的空间大小一开始就分配好了，栈帧过多超过了栈的大小导致。
 */
public class StackOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackOF oom = new StackOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
