

/**
 * @author huchonglin
 * @date 2021/3/29 11:10
 * 测试String.itern()
 */
public class IternDemo {
    /**
     * true
     * false
     */
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
