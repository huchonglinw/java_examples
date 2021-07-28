

/**
 * 类加载测试
 * 调用类的静态变量、常量时，只会触发static的初始化
 * @author huchonglin
 * @date 2021/2/21 21:32
 */
public class ClassLoadDemo {
    static {
        System.out.println("static");
    }
    public static int a;
    public static final String b = "hello";
    public static void staticMethod(){
        System.out.println("static method");
    }


    public ClassLoadDemo(){
        System.out.println("construct");
    }
    {
        System.out.println("construct2");
    }
}

/**
 * A类调用B类的常量时，不会触发A类的static代码块，会触发B类
 */
class SubClass extends ClassLoadDemo{
    static{
        System.out.println("SubClass static");
        String c = b;
    }
    public SubClass(){
        System.out.println("SubClass construct");
    }
}

class OrtherClass{
    private int a = ClassLoadDemo.a;
}

/**
 *
 * 大概面经（不考虑排版了，随便写写）： 包括 自我介绍、 数据库索引、 数据库的存储引擎类型和优缺点、
 * 数据库表是怎么存放数据的、 数据库怎么读写数据、 hashmap（包括底层数据结构、不同版本的对比、put的过程、
 * rehash的过程、怎么解决hash冲突、hashmap为什么线程不安全、会出现什么问题、怎么保证线程安全
 * （可以直接使用Hashtable、currentHashMap、以及Collections下的安全版本map或者通过自己加锁保证线程安全））、
 * 对锁机制的了解、在不用锁的情况下有没有什么办法可以做到线程安全 （ CAS）  、对 volatile的了解、
 * 什么是 指令重排序 、指令重排序可能带来什么样的后果、怎么防止指令重排序、对 微服务组件的理解和了解程度
 * （简历上写了，然后被问了）、讲讲 服务注册和 服务发现的好处？为什么要这么做？
 * DNS域名系统也类似服务注册和服务发现，你觉得两者之间的优劣势是什么？对 HTTP、TCP 的了解？
 * 。哦对了，还有简历上的项目，就简单提问一下做了什么，遇到什么问题，怎么解决？
 * 印象深刻的有两个问题：sharding jdbc要是遇到再分表，支持不，怎么操作？
 * 在迁移数据时，怎么做到用户无感？【 表示不懂，得多去看看分库分表可能会遇到的场景问题了】 😥 😥 😥大概就这些了。
 */
class Main{
    public static void main(String[] args) {
//        new SubClass();
            new OrtherClass();
    }
}
