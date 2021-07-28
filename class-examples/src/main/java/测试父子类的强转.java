import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import 父子类.Sub;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huchonglin
 * @date 2020/12/10 10:52
 */
public class 测试父子类的强转 {
    public static void main(String[] args) throws IllegalAccessException {
        Sub sub = new Sub();
        sub.setErrorMsg("错误信息");
        sub.setName("nam");
        String a = "123";

        System.out.println(checkObjectFieldNull(sub, null));
        System.out.println(checkObjectFieldNull(sub, new String[]{"name","errorMsg"}));
        System.out.println(checkObjectFieldNull(sub, new String[]{"name"}));
        System.out.println(checkObjectFieldNull(sub, new String[]{"errorMsg"}));
//        System.out.println(checkObjectFieldNull(sub, new String[]{"errorMsg"}));
    }


    public static boolean checkObjectFieldNull(Object object, String[] exclusionFields) throws IllegalAccessException {
        if (object == null) {
            return true;
        }
        Field[] declaredFields3 = object.getClass().getDeclaredFields();
        if (declaredFields3.length == 0) {
            return true;
        }
        Class<?> superclass = object.getClass().getSuperclass();

        if (superclass != null) {
            Field[] declaredFields2 = superclass.getDeclaredFields();
            declaredFields3 = ArrayUtils.addAll(declaredFields2, declaredFields3);
        }

        List<Field> fieldList = Arrays.stream(declaredFields3).filter(d -> {
            if (exclusionFields == null) {
                return true;
            }
            return Arrays.stream(exclusionFields)
                    .noneMatch(e -> StringUtils.equals(e, d.getName()));
        }).collect(Collectors.toList());

        for (Field field : fieldList) {
            field.setAccessible(true);
            if (field.get(object) != null && StringUtils.isNotEmpty(field.get(object).toString().trim())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkObjFieldIsNull(Object object) throws IllegalAccessException {
        boolean flag = false;
        if (null != object) {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);//在用反射时访问私有变量（private修饰变量）
                if (field.get(object) == null || field.get(object).equals("")) {
                    flag = true;
                    return flag;
                }
                if (field.get(object) != null && field.get(object).toString().trim().equals("")) {
                    flag = true;
                    return flag;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

}
