package po.父子类;

import lombok.Data;

/**
 * @author huchonglin
 * @date 2020/11/17 18:45
 */
@Data
public class Person {

        private String name;  // 姓名
        private int salary; // 薪资
        private String age; // 年龄
        private String sex; //性别
        private String area;  // 地区

        // 构造方法
        public Person(String name, int salary, String age, String sex, String area) {
            this.name = name;
            this.salary = salary;
            this.age = age;
            this.sex = sex;
            this.area = area;
        }

        // 省略了get和set，请自行添加

}
