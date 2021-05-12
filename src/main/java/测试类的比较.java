import org.junit.Test;
import po.父子类.Base;
import po.Excel;
import po.父子类.Sub;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huchonglin
 * @date 2020/12/9 16:05
 */
public class 测试类的比较 {
    public static void main(String[] args) {
//        test(new Base());
//        test2();
        }
    public static void test(Object x){
        System.out.println("Testing x of type=     " + x.getClass());
        System.out.println("x instanceof Base=     " + (x instanceof Base));
        System.out.println("x instanceof Derived=     " + (x instanceof Sub));
        System.out.println("Base.isInstance(x)=     " + Base.class.isInstance(x));
        System.out.println("Derived.isInstance(x)=     " + Sub.class.isInstance(x));
        System.out.println("x.getClass() == Base.class=     " + (x.getClass() == Base.class));
        System.out.println("x.getClass() == Derived.class=     " + (x.getClass() == Sub.class));
        System.out.println("x.getClass.equals(Base.class)=     " + (x.getClass().equals(Base.class)));
        System.out.println("x.getClass.equals(Derived.class)=     " + (x.getClass().equals(Sub.class)));
        System.out.println("Excel");
    }


    @Test
    public void test3(){
        Field[] declaredFields = Sub.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Class<? extends Annotation> annotationType = declaredField.getAnnotation(Excel.class).annotationType();
            System.out.println(1);
        }

    }

}
