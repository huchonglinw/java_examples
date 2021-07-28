package lambda;

import po.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huchonglin
 * @date 2020/11/17 18:46
 */
public class PersonTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "23", "male", "New York"));
        personList.add(new Person("Jack", 7000, "25", "male", "Washington"));
        personList.add(new Person("Lily", 7800, "21", "female", "Washington"));
        personList.add(new Person("Anni", 8200, "24", "female", "New York"));

        Map<String, Person> map = personList
                .stream()
                .filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toMap:" + map);
    }

}
