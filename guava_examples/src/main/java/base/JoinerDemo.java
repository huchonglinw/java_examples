package base;

import com.google.common.base.Joiner;
import org.junit.Test;

/**
 * @author: hcl
 * @date: 2021/7/20 15:13
 */
public class JoinerDemo {
    @Test
    public void test1() {
        String join = Joiner.on(",").useForNull("nulltest").join("abvsd", "zxcqwe", "", null);
        String join2 = Joiner.on(",").skipNulls().join("abvsd", "zxcqwe", "", null);
        System.out.println(join);
    }
}
