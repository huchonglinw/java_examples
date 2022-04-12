package thread;

/**
 * 模仿阻塞线程
 *
 * @author: chonglin.hu
 * @date: 2021/9/30 19:44
 */
public class SimulationBlockedThread {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                // 模拟阻塞IO
                // 调用了阻塞的API
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
