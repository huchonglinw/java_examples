package jdk测试;

import java.util.List;

/**
 * @author huchonglin
 * @date 2020/12/19 16:02
 */
public class 测试线程并发对静态类的影响 {
    public static void main(String[] args) {

        new Thread(()->{
            List<String> staticList = 静态类.getStaticList();
            System.out.println(staticList);
        }).start();

        new Thread(()->{
            List<String> staticList = 静态类.getStaticList();
            System.out.println(staticList);
        }).start();

    }
}
