package collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author: chonglin.hu
 * @date: 2022/1/25 21:01
 */
public class SplitTest {
    /**
     * 测试集合拆分
     */
    @Test
    public void test() {
        List<String> list = Lists.newArrayList("123","234","345");
        List<List<String>> partition = Lists.partition(list, 2);
        for (List<String> strings : partition) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("next split");
        }
    }
}
