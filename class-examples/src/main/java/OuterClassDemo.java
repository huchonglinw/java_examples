/**
 * 测试内部类
 *
 * @author: hcl
 * @date: 2021/7/19 10:31
 */
public class OuterClassDemo {
    /**
     * 测试内部类调用外部类的方法
     */
    public void say() {
        System.out.println("outer class say");
    }

    private final class InnerClass {
        public void say() {
            System.out.println("inner class say");
        }
    }

    public static void main(String[] args) {
        OuterClassDemo outerClassDemo = new OuterClassDemo();
//        InnerClass innerClass = OuterClassDemo.InnerClass;
    }
}
