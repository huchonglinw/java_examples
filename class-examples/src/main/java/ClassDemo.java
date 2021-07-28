import org.junit.Test;

import java.io.Serializable;

/**
 * @author huchonglin
 * @date 2021/1/16 17:56
 */
public class ClassDemo {
    /**
     * 测试类型转换
     */
    @Test
    public void test(){
        int i = 0;
        System.out.println((Integer & Serializable) i );
    }
}
