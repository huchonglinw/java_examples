/**
 * @author huchonglin
 * @date 2021/3/18 10:32
 * 测试split
 */
public class SplitDemo {
    public static void main(String[] args) {
        String str = "usergroup1";
        String str2 = "usergroup1,usergorup2";


        final String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
