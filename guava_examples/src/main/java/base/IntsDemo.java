package base;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: hcl
 * @date: 2021/7/20 14:56
 */
public class IntsDemo {
    @Test
    public void test1() {
        int[] arr = new int[]{1,2,3};
        List<int[]> ints = Arrays.asList(arr);
        List<Integer> integers = Ints.asList(arr);
    }

    public static void main(String[] args) {
    }
}
