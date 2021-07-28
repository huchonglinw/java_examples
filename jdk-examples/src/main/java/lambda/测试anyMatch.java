package lambda;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huchonglin
 * @date 2020/12/4 11:03
 */
public class 测试anyMatch {
    @Test
    public void test1(){
        List<String> strs = new LinkedList<>();
        strs.add("111111");
        strs.add("222222");
        strs.add("333333");
        strs.add("444444");

        String s = "111";
        String xx = "555";
        String xxx = "2";
        List<String> str = new LinkedList<>();
        str.add(s);
        str.add(xx);
        str.add(xxx);

        str.stream()
                .filter(s1 -> {
                    return strs.stream().anyMatch(s2 -> 1 == 1);
                }).forEach(System.out::println);

    }
}
