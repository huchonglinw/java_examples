/**
 * this关键字测试
 * @author: hcl
 * @date: 2021/7/19 10:12
 */
public class ThisDemo {
    private int state;

    public void say() {
        System.out.println("say hello");
    }

    public void test() {
//        This2Demo.this.say();
        this.say();
    }

    public static void main(String[] args) {
        ThisDemo thisDemo = new ThisDemo();
        thisDemo.test();
    }
}
