package thread;

/**
 * 判断blocker属性
 * @author: hcl
 * @date: 2021/7/16 11:32
 */
public class ThreadBlocker {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("t start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(runnable);

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

}
