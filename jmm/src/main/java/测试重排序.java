import lombok.SneakyThrows;

/**
 * @author huchonglin
 * @date 2021/2/19 23:05
 */
public class 测试重排序 {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    a = 1;
                }
            });
            thread.start();

            int finalI = i;
            Thread thread1 = new Thread(new Runnable() {

                @SneakyThrows
                @Override
                public void run() {
                    if(a == 0) {
                        System.out.println(finalI + "," +a);
                    }
                }
            });
            thread1.start();
            thread.join();
            thread1.join();

        }
    }
}
