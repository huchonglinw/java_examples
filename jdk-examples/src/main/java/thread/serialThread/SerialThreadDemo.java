package thread.serialThread;

/**
 * @author: hcl
 * @date: 2021/7/22 23:54
 */
public class SerialThreadDemo {
    public static void main(String[] args) {
        SerialThreadContext serialThreadContext = new SerialThreadContext();
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                System.out.println("b");
            }
        };
        Runnable c = new Runnable() {
            @Override
            public void run() {
                System.out.println("c");
            }
        };

        serialThreadContext.submit(100,a,b,c);
    }
}
