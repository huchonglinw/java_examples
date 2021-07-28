import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hcl
 * @date: 2021/7/21 11:58
 */
public class getCharsDemo {
    public static void main(String[] args) {
        String s = new String("testabc");
        char[] dst = new char[3];

        // 当srcEnd - srcBegin > dst.length 的时候 会报错
        s.getChars(1,dst.length + 1,dst,0);
        String string = new String(dst);
        System.out.println(string);
    }

    /**
     * 将String拷贝到char数组
     */
    @Test
    public void getCharTest() {
        String Str1 = new String("www.runoob.com");
        char[] Str2 = new char[6];

        try {
            Str1.getChars(4, 10, Str2, 0);
            System.out.print("拷贝的字符串为：" );
            System.out.println(Str2 );
        } catch( Exception ex) {
            System.out.println("触发异常...");
        }
    }


    /**
     * 将数组1转移到数组2中
     */
    @Test
    public void systemArrayCopyTest() {

//        String s = new String("testabc");
        char[] testabcs = new String("testabc").toCharArray();
        char[] dst = new char[3];
        // 当srcEnd - srcBegin > dst.length 的时候 会报错
//        s.getChars(1,dst.length + 1,dst,0);
        System.arraycopy(testabcs,1,dst,0,3);
        String string = new String(dst);
        System.out.println(string);
        ArrayList<Object> objects = new ArrayList<>();
    }

    @Test
    public void systemArrayCopyTest2() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("123");
        list1.add("456");
        System.arraycopy(list1, 0, list2, 0, 1);
    }
}
