import org.junit.Test;

/**
 * 位运算测试
 *
 * @author: chonglin.hu
 * @date: 2021/9/27 14:47
 */
public class BitDemo {

    /**
     * 0xffff -》 一位有16种可能，16*16*16*16 = 65536
     */
    @Test
    public void test1() {
        int a = 0xffff;
        System.out.println("0xffff," + a + "," + Integer.toBinaryString(a)); // 65535,1111111111111111

        a = 0x7fff;
        System.out.println("0x7fff," + a + "," + Integer.toBinaryString(a)); // 32767,0111111111111111

        a = 0x007e;
        System.out.println("0x007e," + a + "," + Integer.toBinaryString(a)); // 126,  0000000001111110

        a = 0xfffe;
        System.out.println("0xfffe," + a + "," + Integer.toBinaryString(a)); // 65534,1111111111111110

        a = 0xffff << 16;
        System.out.println("0xffff << 16," + a + "," + Integer.toBinaryString(a)); // 65534,1111111111111110

        System.out.println("0xffff & 0x7fff," + (0xffff & 0x7fff));

    }

    /**
     * & 测试
     * 当 x>32767 & 0x7fff 时，值会改变
     * 不相同，32768，res = 0
     * 不相同，32769，res = 1
     * 不相同，32770，res = 2
     *
     * 当cons 为 0x7ffd时，不同的有很多
     * 不相同，2，res = 0
     * 不相同，3，res = 1
     * 不相同，6，res = 4
     * 不相同，7，res = 5
     * 不相同，10，res = 8
     * 不相同，11，res = 9
     */
    @Test
    public void test2() {
        int cons = 0x7fff; // 4096 => 111111111111111
        for (int i = 0; i < 50000; i++) {
            int res = i & cons;
            if (res != i) {
                System.out.println("不相同，" + i + "，" + "res = "+ res);
            }
        }
    }

    /**
     * 移位
     */
    @Test
    public void test() {
        System.out.println(1 << 31); // -2147483648
        System.out.println(1 << 32); // 1
        System.out.println(1 << 30); // 1073741824
        System.out.println(Integer.MAX_VALUE); // 2147483647

        System.out.println(1 << 1); // 2
    }

    /**
     * 移位
     */
    @Test
    public void test3() {
        int i = -3;
        System.out.println("Before << , i's value is " + i);
        System.out.println("i's binary string is " + Integer.toBinaryString(i));
        i <<= 10;
        System.out.println("After << , i's value is " + i);
        System.out.println("i's binary string is " + Integer.toBinaryString(i));
    }
}
