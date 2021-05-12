package lambda;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huchonglin
 * @date 2020/11/20 18:25
 */
public class GoodsCatergoryTest {
    public static void main(String[] args) {
        List<String> objects = new LinkedList<>();
        objects.add("xxxxx");
        objects.add("yyyyy");
        objects.add("yyyyyxxx");
        objects.add("yyyyyxxxxxx");

        LinkedList<String> objects1 = new LinkedList<>();


        objects.stream()
                .filter(x ->{
                    boolean b = Stream.of("xxxxx", "yyyyy").noneMatch(x::equals);
                    if(b) {
                        objects1.add(x);
                    }
                    return !b;
                }).forEach(System.out::println);
        System.out.println("=====");
        System.out.println(objects1);

    }
}
