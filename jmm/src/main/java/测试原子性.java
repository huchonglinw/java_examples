/**
 * @author huchonglin
 * @date 2021/2/20 15:19
 */
public class 测试原子性 {
    /**
     * 结果 < 20000
     */
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        });

        final Thread thread = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        });

        thread1.start();
        thread.start();
        thread1.join();
        thread.join();
        System.out.println(i);


    }
}
