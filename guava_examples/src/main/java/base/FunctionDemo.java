package base;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hcl
 * @date: 2021/7/20 17:32
 */
public class FunctionDemo {
    @Test
    public void functionTest() {
        Function<Integer, String> function = new Function<Integer, String>() {

            @Override
            public @Nullable String apply(@Nullable Integer input) {
                if (input == null) {
                    return null;
                }
                return "" + input + "";
            }
        };

        int a = 100;
        String apply = function.apply(a);
        System.out.println(apply);

        ArrayList<Integer> arrayList = Lists.newArrayList(1, 2, 3);
        List<String> transform = Lists.transform(arrayList, function);
        // 延时调用
        System.out.println(transform);

        arrayList.add(4); // 保存的是引用
        System.out.println(transform);

    }

    @Test
    public void predicateTest() {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input != null && input < 5;
            }
        };

//        Iterables.tryFind()
    }
}
