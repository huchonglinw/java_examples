/**
 * @author: chonglin.hu
 * @date: 2022/1/6 22:38
 */
public class 匿名内部类 {
    public void test() {
        class InnerClass {
            public void print() {
                System.out.println("hello");
            }
        }
    }

    public static void main(String[] args) {
        匿名内部类 a = new 匿名内部类();

    }



}
