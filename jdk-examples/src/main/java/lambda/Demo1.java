package lambda;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import po.Person;
import po.Person1;
import po.Trader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Demo1 {

    private static List<String> listDemo = Arrays.asList("345", "222222222", "123123", "8564", "6544", "1235", "13", "32423");


    /**
     * 一个用户有多个号码
     * 要求返回 号码，用户 的map
     * 1.遍历用户集合
     * 2.peek
     * 3.
     */
    @Test
    public void test() {
        Person1 person1 = new Person1();
        person1.setName("小明");
        List<String> telPhoneList = new LinkedList<>();
        telPhoneList.add("mmm123854982123");
        telPhoneList.add("mmm8867531");
        telPhoneList.add("mmm123593106341");
        person1.setTelPhoneNumList(telPhoneList);

        Person1 person12 = new Person1();
        person12.setName("小白");
        List<String> telPhoneList2 = new LinkedList<>();
        telPhoneList2.add("bbbbb1231231");
        telPhoneList2.add("bbb8865481");
        telPhoneList2.add("bbb123545936341");
        person12.setTelPhoneNumList(telPhoneList2);

        List<Person1> person1List = new LinkedList<>();
        person1List.add(person1);
        person1List.add(person12);

        /**
         * 返回很多个 手机号码 与 person 映射的map
         * 再将map组合起来
         */
        Map<String, String> stringStringMap = person1List.stream()
                .flatMap(p -> p.getTelPhoneNumList()
                        .stream()
                        .map(c -> {
                            Map<String, String> map = new HashMap<>(64);
                            map.put(c, p.getName());
                            return map;
                        }))
                .reduce((x, y) -> {
                    x.putAll(y);
                    return x;
                }).orElse(null);
        System.out.println(stringStringMap);

        System.out.println("====");
//        System.out.println(map);

        /**
         * foreach操作
         */
//        person1List.stream()
//                .forEach(p ->{
//                    p.getTelPhoneNumList()
//                            .stream()
//                            .forEach(System.out::println);
//                });

        System.out.println("====");

    }

    /**
     * 有一个Person对象集合
     * 要求返回3个集合，名字集合，年龄集合，地区集合。
     */
    @Test
    public void test22() {
        Person person = new Person("小名", 8000, "15", "男", "广东");
        Person person2 = new Person("小名", 8000, "47", "男", "江西");
        Person person3 = new Person("小名", 8000, "22", "男", "北京");
        List<Person> people = Arrays.asList(person, person2, person3);

        List<List<String>> lists = people.stream()
                .map(p -> Arrays.asList(
                        Arrays.asList(p.getArea()),
                        Arrays.asList(p.getName())))
                .collect(Collectors.toList())
                .get(0);
        System.out.println(lists.get(0));
        System.out.println(lists.get(1));
    }

    /**
     * 测试String[] strs 的过滤
     */
    @Test()
    public void test32() {
        String[] strs = {"12312", "231231", "123", "321"};
        List<String> list = Collections.singletonList("321");
        list.stream()
                .filter(l -> {
                    boolean b = Stream.of(strs)
                            .noneMatch(x -> x.equals(l));
                    return b;
                }).forEach(System.out::println);

    }

    /**
     * 测试filter的性质，只有为true才能进入下一步
     */
    @Test
    public void test44() {
        List<String> strings = Arrays.asList("111", "222", "333");
        strings.stream()
                .filter(x -> x.equals("1111231"))
                .forEach(e ->{
                    System.out.println(e);
                });
    }


    /**
     * 测试多条件分组
     * 测试分组 一
     * 返回一个对象，格式为(name="xx", age=["xx", "xx", "xx"], area=["xx", "xx"])
     */
    @Test
    public void test11() {
        Person person = new Person("小名", 8000, "15", "男", "广东");
        Person person2 = new Person("小名", 8000, "47", "男", "江西");
        Person person3 = new Person("小名", 8000, "22", "男", "北京");
        Person person4 = new Person("小名3", 8000, "22", "男", "北京");
        Person person5 = new Person("小名5", 8000, "22", "男", "北京");
        List<Person> people = Arrays.asList(person, person2, person3,person4,person5);
        /**
         * 多条件分组
         */
//        people.stream()
//                .collect(Collectors.groupingBy(e -> e.getName() +"#" +e.getAge());


//        people.stream()
//                .collect(Collectors.toMap());
//        List<String[]> collect = people.stream()
//                .collect(Collectors.groupingBy(Person::getName))
//                .values()
//                .stream()
//                .map(p -> new String[]{
//                        p.stream().map(Person::getAge)
//                                .distinct()
//                                .reduce((x, y) -> x.concat(",").concat(y))
//                                .orElse(""),
//                        p.stream().map(Person::getArea)
//                                .distinct()
//                                .reduce((x, y) -> x.concat(",").concat(y))
//                                .orElse(""),
//                })
//                .collect(Collectors.toList());
//        for (String[] strings : collect) {
//            for (String string : strings) {
//                System.out.println(string);
//            }
//        }

        //方法一
//        List<Person> collect = people.stream()
//                .collect(Collectors.groupingBy(Person::getName))
//                .values()
//                .stream()
//                .map(personList -> {
//                    Person person1 = personList.get(0);
//                    person1.setArea(personList
//                            .stream()
//                            .map(Person::getArea)
//                            .distinct()
//                            .reduce((x, y) -> x + "," + y)
//                            .orElse(null));
//                    person1.setAge(personList
//                            .stream()
//                            .map(Person::getAge)
//                            .distinct()
//                            .reduce((x, y) -> x + "," + y)
//                            .orElse(null));
//                    return person1;
//                })
//                .collect(Collectors.toList());
//        System.out.println(collect.size());
//        System.out.println(collect);
        //Person(name=小名, salary=8000, age=15,47,22, sex=男, area=广东,江西,北京)
    }

    /**
     * 8
     *
     * @return: void
     * @author: huchonglin
     * @date: 2020/11/13 11:33
     */
    @Test
    public void test1() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }


    /**
     * @return: void
     * @author: huchin
     * @date: 2020/11/13 11:35
     */
    @Test
    public void testReduce() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);
    }


    /**
     * 排序
     */
    @Test
    public void test5() {
//        8564
//        6544
//        345
//        32423
//        1235
//        123123
        Stream.of("345", "123123", "8564", "6544", "1235", "13", "32423")
                .filter(s -> Integer.valueOf(s) > 100)
                .sorted(Comparator.reverseOrder())
                .forEach(s -> {
                    System.out.println(s);
                });
    }

    @Test
    public void test6() {
        listDemo.stream()
                .sorted()
                .forEach(System.out::println);
    }


    /**
     * @param:
     * @return: void
     * @author: huchonglin
     * @date: 2020/11/13 15:12
     */
    @Test
    public void puttingIntoPractice() {
        HashMap<Object, Object> map = new HashMap<>();
        Trader trader1 = new Trader("1", "1");
        Trader trader11 = new Trader("1", "1");
        Trader trader2 = new Trader("2", "2");
        map.put(trader1, trader1);
        map.put(trader1, trader11);
        map.put(trader1, trader2);

    }

    @Test
    public void test7() {
        Integer i = 0;
        System.out.println(i == null);
    }

    @Test
    public void test33() {
        String s1 = new String("xxxxxx");
        String s2 = new String("xxxxxx");
        System.out.println(ObjectUtils.notEqual(s1, s2));

    }
}

