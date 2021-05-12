package lambda;

import org.junit.Test;
import po.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author huchonglin
 * @date 2020/11/23 14:02
 */
public class 多条件去重 {
    static List<Person> personList = new ArrayList<Person>();

    static {
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        personList.add(new Person("Tom1", 8900, "23", "male", "New York"));
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        for (int i = 0; i < 10000; i++) {
            personList.add(new Person("Tom" + i, 8900, "23", "male", "New York"));
        }
    }

    public static void main(String[] args) {

        List<Integer> needDeletedDataNum = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            String str = person.getName().concat(String.valueOf(person.getSalary())).concat(person.getAge());
            Integer it = map.get(str);
            if (it == null) {
                map.put(str, i);
                continue;
            }
            needDeletedDataNum.add(i);
            int thisNum;
            if ((thisNum = map.get(str)) != -1) {
                needDeletedDataNum.add(thisNum);
                map.put(str, -1);
            }
        }
        Iterator<Person> iterator = personList.iterator();
//        while(iterator.hasNext()){
//            iterator.next()
//        }
        System.out.println(personList);
        System.out.println(1);

//        List<Person> collect = personList.stream()
//                .filter(distinctByKey(Person::getAge)).collect(Collectors.toList());
//        System.out.println(collect);
//
//        List<Person> studentDistinctList = personList.stream()
//                .collect(Collectors.collectingAndThen
//                        (Collectors.toCollection(() ->
//                                        new TreeSet<>(Comparator.comparing(t -> t.getName()))),
//                                ArrayList::new
//                        )
//                );
//        System.out.println(studentDistinctList);

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * 按某个字段去重
     * 去重之后的数据会保留一条
     */
    @Test
    public void test3() {
        long startTime = System.currentTimeMillis();   //获取开始时间
//        personList = personList.stream().collect(Collectors.collectingAndThen(Collectors
//                .toCollection(() -> new TreeSet<Person>(Comparator.comparing(Person::getName).thenComparing().)), ArrayList::new));
//        System.out.println(personList);

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }


}
