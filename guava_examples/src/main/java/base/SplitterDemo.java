package base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: hcl
 * @date: 2021/7/20 15:30
 */
public class SplitterDemo {
    /**
     * 测试jdk split和 guava Splitter的区别
     */
    @Test
    public void test2() {
        String str = "，a，b，c，";
        String[] split = str.split("，");
        System.out.println(split.length); // 4 不会去除头元素

        List<String> omit = Splitter.on("，").omitEmptyStrings().splitToList(str);
        List<String> notomit = Splitter.on("，").splitToList(str);
        System.out.println(omit.size()); // 3
        System.out.println(notomit.size()); // 5 所有的空串都包含
    }

    /**
     * 测试jdk split和 guava Splitter的区别
     */
    @Test
    public void test3() {
        String str = "，a，b，c，，";
        String[] split = str.split("，");
        System.out.println(split.length); // 4 包含了头元素

        List<String> omit = Splitter.on("，").omitEmptyStrings().splitToList(str);
        List<String> notomit = Splitter.on("，").splitToList(str);
        System.out.println(omit.size()); // 3
        System.out.println(notomit.size()); // 6 所有的空串都包含
    }

    /**
     * Splitter用法
     */
    @Test
    public void test1() {
        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");

//        split.iterator()
    }

    /**
     * trimResults可以接收一个CharMatcher，但只会过滤掉这个字符（不会去除掉这个元素）。
     */
    @Test
    public void trimResultTest() {
        String str = "abc,efg,hij,hello,";
        List<String> is = Splitter.on(",")
                .trimResults(CharMatcher.is('e'))
                .splitToList(str);
        for (String s : is) {
            System.out.println(s);
        }

        List<String> anyOf = Splitter.on(",")
                .trimResults(CharMatcher.anyOf("efg"))
                .splitToList(str);
        for (String s : anyOf) {
            System.out.println(s);
        }
    }

    @Test
    public void patternTest() {
        List<String> commands = Splitter.on("|").trimResults().splitToList("cat xxxx.file | grep name");
        for (String command : commands) {
            System.out.println(command);
        }
    }
}
