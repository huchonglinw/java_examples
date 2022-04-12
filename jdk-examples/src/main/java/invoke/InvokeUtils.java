package invoke;

import java.lang.reflect.Field;

/**
 * @author: chonglin.hu
 * @date: 2021/9/26 19:07
 */
public class InvokeUtils {

    /**
     * 反射获取object中fieldName的值
     *
     * @throws NoSuchFieldException
     */
    public static Object getFieldValueFromObject(String fieldName, Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }


    // ====错误例子以及测试样例===============

    /**
     * 反射获取object中fieldName的值
     * 这个例子会报错，找不到字段。因为getFiled只能获取public的
     *
     * @throws NoSuchFieldException
     */
    public static Object getFieldValueFromObjectError(String fieldName, Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setPersonId("personId");
        person.setUserName("personName");

//        InvokeUtils.getFieldValueFromObjectError("personId", person);
        InvokeUtils.getFieldValueFromObject("personId", person);
    }


    private static class Person {
        private String userName;
        protected String personId;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }
    }
}
