import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author: chonglin.hu
 * @date: 2022/1/28 15:56
 */
public class RegaxDemo {
    /**
     * tag_(.*)_appId_(.*)
     *
     * {1,} = + 代表多个
     */
    @Test
    public void tes() {
        String r1 = "^[a-zA-Z0-9_]+$";
        String s1 = "class5_group4_student2";
        System.out.println(s1.matches(r1)); // true



        String r2 = "tag_.*_appId_.*";
        String s2 = "tag_class5_group4_student2_appId_12321";
        System.out.println(s2.matches(r2)); //true

    }

    @Test
    public void test2() {
        String r2 = "tag_.*_appId_.*";
        String s2 = "tag_class5_group4_student2appId_12321";
        System.out.println(s2.matches(r2)); //false
    }

    @Test
    public void test3() {
        String r2 = "tag_.*_appId_[0-9]+";
        String s2 = "tag_class5_group4_student2_appId_1232a1";
        System.out.println(s2.matches(r2)); //false
    }

    @Test
    public void Test() {
     String s = ".*_cycleRatio";
        Pattern compile = Pattern.compile(s);
    }
}
