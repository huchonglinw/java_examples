import org.junit.Test;
import po.Person;
import po.父子类.Base;
import po.父子类.Sub;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author huchonglin
 * @date 2020/11/23 21:39
 */
public class 测试变量的传递性 {
    public static void main(String[] args) {

    }

    @Test
    public void test(){
        Person person = new Person("小名", 8000, "15", "男", "广东");
        Person person2 = new Person("小名", 8000, "47", "男", "江西");
        Person person3 = new Person("小名", 8000, "22", "男", "北京");
        List<Person> people = new LinkedList<>();
        people.add(person);
        people.add(person2);
        people.add(person3);
        List<List<Person>> personList = new LinkedList<>();
        personList.add(people);
        change4(personList);

//        change(people);
//        change2(people);
//        change3(people);
        System.out.println(people);
        System.out.println(personList);
        change5(Arrays.asList("123123123","zxczxcads","123"));
        change6();

        Sub base = new Sub();
        base.setSum(1);
        change7(base);
        System.out.println("change7==== "+ base);
    }

    private void change7(Base base) {
        System.out.println("===change7");
        base.setName("11");
    }

    private void change4(List<List<Person>> personList) {
        List<Person> people = personList.get(0);
        people.add(new Person("xx",900,"13","男","gd"));
    }

    private void change3(List<Person> people) {
        people.add(new Person("111",11,"11", "11","11"));
    }

    private void change2(List<Person> person) {
        Person person1 = person.get(0);
        person1.setAge("我也设置值了");
    }

    private void change(List<Person> person) {
        Person person1 = person.get(0);
        person1.setName("我设置了");
    }

    private void change5(Collection<?> collection){
        Iterator<?> iterator = collection.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return;
    }

    private void change6(){
        System.out.println("okokok");
    }
}
