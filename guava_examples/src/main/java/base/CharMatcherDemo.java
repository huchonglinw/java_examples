package base;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * @author: hcl
 * @date: 2021/7/20 16:40
 */
public class CharMatcherDemo {
    @Test
    public void test1() {
        String str = "abc1234abcab,a,b,c,a,a,";
        CharMatcher abc = CharMatcher.anyOf("abc");
        String s = abc.removeFrom(str);
        String s1 = abc.retainFrom(str);

        System.out.println(s);
        System.out.println(s1);

        CharMatcher abc1 = CharMatcher.anyOf("abc").or(CharMatcher.is('2')).or(CharMatcher.is(','));
        String s2 = abc1.retainFrom(str);
        System.out.println(s2);
    }



}
