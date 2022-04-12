package thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chonglin.hu
 * @date: 2021/10/13 11:40
 */
public class 测试多线程修改Map {
    public static Map<String, String> generalMap;
    static {
        generalMap = new HashMap<>();
        generalMap.put("1", "a");
    }
    public static void main(String[] args) {
        new Thread(() -> {
            String s = generalMap.get("1");
            System.out.println(Thread.currentThread() + "output = " + s);
        }).start();

        new Thread(() -> {
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("1", "a");
            objectObjectHashMap.put("2", "b");
        });
    }
}
