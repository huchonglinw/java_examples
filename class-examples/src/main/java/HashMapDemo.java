import org.junit.Test;

import java.util.HashMap;

/**
 * @author huchonglin
 * @date 2021/1/22 20:28
 */
public class HashMapDemo {
    /**
     * 测试HashMap的哈希冲突
     * 哈希值一样，但是equals不一样。
     */
    @Test
    public void test(){
        HashMapDemoObject o1 = new HashMapDemoObject();
        o1.setId(1);
        HashMapDemoObject o2 = new HashMapDemoObject();
        o2.setId(2);
//        System.out.println(o1.equals(o2)); false
        HashMap<Object, Object> map = new HashMap<>();
        map.put(o1,o1);
        map.put(o2,o2);
        System.out.println(map.get(o1));
        System.out.println(map.get(o2));
    }

}
