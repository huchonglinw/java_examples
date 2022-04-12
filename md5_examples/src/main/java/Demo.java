import org.junit.Test;

/**
 * @author: chonglin.hu
 * @date: 2021/12/22 14:46
 */
public class Demo {
    @Test
    public void test() {
        // BC4A73B882259E2F4FCE11FCF500A3C4
        String s1 = MD5Util.MD5Encode("asddddddddddddddddddddd123123213213213ddddddddddddddddddddddddddddddd");
        System.out.println(s1);
        System.out.println(s1.length());

    }
}
