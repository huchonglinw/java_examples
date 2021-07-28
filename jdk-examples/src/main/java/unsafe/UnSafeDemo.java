package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: hcl
 * @date: 2021/7/23 11:05
 */
public class UnSafeDemo {

    private volatile Object listeners;

    private static UnsafeHelper unsafeHelper;

    static {
        unsafeHelper = new UnsafeHelper();
    }

    static class UnsafeHelper {
        private static Unsafe unsafe = null;
        static long LISTENERS_OFFSET;


        static {
            Field field;
            try {
                field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                unsafe = (Unsafe) field.get(null);
                try {
                    LISTENERS_OFFSET = unsafe.objectFieldOffset(UnSafeDemo.class.getDeclaredField("listeners"));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        boolean cas(UnSafeDemo unSafeDemo, Object expect, Object update) {
            return unsafe.compareAndSwapObject(unSafeDemo, LISTENERS_OFFSET, expect, update);
        }

        boolean getOffset(UnSafeDemo unSafeDemo, Object expect, Object update) {
            return unsafe.compareAndSwapObject(unSafeDemo, LISTENERS_OFFSET, expect, update);
        }
    }

    void test () {
        Object o = new Object();
        Object o1 = new Object();
        boolean cas = unsafeHelper.cas(this, null, o);
        boolean cas1 = unsafeHelper.cas(this, o, o1);
        System.out.println(cas && cas1);
    }

    public static void main(String[] args) {
        UnSafeDemo unSafeDemo = new UnSafeDemo();
        unSafeDemo.test();
        UnSafeDemo unSafeDemo1 = new UnSafeDemo();
        unSafeDemo1.test();
    }
}
