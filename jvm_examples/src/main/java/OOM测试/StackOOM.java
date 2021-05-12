package OOM测试;

/**
 * @author huchonglin
 * @date 2021/3/29 10:28
 */

/**
 * 没有足够的内存为新线程分配虚拟机栈，OOM
 */
public class StackOOM {
    public static void process(){
        while(true){
            process();
        }
    }
    public static void main(String[] args) {
        while(true) {
            new Thread(() -> process()).start();
        }
    }
}
