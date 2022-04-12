package optional;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author: hcl
 * @date: 2021/8/5 19:18
 */
public class OptionalDemo {

    public OptionalDemo() {
    }

    static Object say(){
        System.out.println("hello");
        return new Object();
    }

    //===
    /**
     * 测试orElse()和orElseGet()
     */
    public void test() {
//        OptionalDemo optionalDemo = new OptionalDemo("hello");
//        String str = null;
//        Optional.ofNullable(str).orElseGet(() -> String.valueOf(getObject()));
//        System.out.println(optionalDemo1);
    }

    private String name;


//    public OptionalDemo get(){
//        return new OptionalDemo("hello");
//    }

    public Object getObject() {
        System.out.println("hi");
        return new Object();
    }

    //===

    @Test
    public void orElseTest() {
        String nullValue = null;
        String optional = Optional.ofNullable(nullValue).orElse("Su");
        System.out.println(optional);

        String optionalGet = Optional.ofNullable(nullValue).orElse("Xiao");
        System.out.println(optionalGet);
        String nonNullOptional = Optional.ofNullable("Susan").orElse("Su");
        System.out.println(nonNullOptional);
        String nonNullOptionalGet = Optional.ofNullable("Molly").orElse("Xiao");
        System.out.println(nonNullOptionalGet);
    }

    /**
     * 测试 Optional<类>的orElseGet
     */
    @Test
    public void testorElseGet() {
//        Optional<OptionalDemo> hello = Optional.of(new OptionalDemo("hello"));
    }

    class Man{
        private String name;

        public Man(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Man{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * OptionalHelper工具类
     *
     */
    static class OptionalHelper {
        public static <T> Optional<T> orElseGet(@NotNull Optional<T> value, Supplier<? extends Optional<T>> other){
            boolean present = value.isPresent();
            return present ? value : other.get();
        }
    }

    @Test
    public void OptionalHelperOrElseGetTest() {
        Optional<Man> man = Optional.of(new Man("man"));
        Optional<Man> empty = Optional.empty();
        Optional<Man> newMan = OptionalHelper.orElseGet(empty, () -> Optional.of(new Man("i am a new man")));
        System.out.println(newMan);
    }

    /**
     * 测试 stream流返回optional，看看结果是list还是optional
     */
    public void test23() {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Optional<String> s = list.stream().filter(l -> l != "123123").findFirst().map(l -> {
            return l + "new";
        });
    }
}
