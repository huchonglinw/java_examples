package thread;

/**
 * 需求：父进程创建子线程，要求父进程在子进程执行结束后退出
 *
 * @author: chonglin.hu
 * @date: 2021/9/16 9:52
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello " + i);
            }
        });
        thread.start();
        thread.join();
        System.out.println("die");
    }

    public void test(String id) {

    }
}
