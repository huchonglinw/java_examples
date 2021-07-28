package lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huchonglin
 * @date 2020/12/7 11:33
 */
public class 测试filter {

    public static void main(String[] args) {
        /**
         * 测试过滤的数据传递性
         */
        LinkedList<String> strs = new LinkedList<>();
        strs.add("11");
        strs.add("22");
        strs.add("33");
        test4(strs);
        test3(strs);
    }

    private static void test4(List<String> strs) {
        strs = test3(strs);
    }

    /**
     * 测试集合过滤之后，对原集合的影响，以及结果集的顺序大小变化
     */
    @Test
    public void test1(){
        LinkedList<String> strs = new LinkedList<>();
        strs.add("11");
        strs.add("11");
        strs.add("11");
        strs.add("11");
        strs.add("11");
        strs.add("22");
        strs.add("33");
        List<String> collect = strs.stream().filter(f -> !StringUtils.equals("11", f)).collect(Collectors.toList());
        System.out.println("===");
    }

    /**
     * 过滤消息
     * @param strs
     * @return
     */
    private static List<String> test3(List<String> strs) {
        List<String> collect = strs.stream().filter(f -> !f.equals("11")).collect(Collectors.toList());
        return collect;
    }

    @Test
    public void test(){
        final int[] rowNum = {1};
        test2(rowNum);
        System.out.println(rowNum[0]);
        LinkedList<Object> objects = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(i);
        }
        List<Object> collect = objects.stream().filter(f -> {
            return true;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    private void test2(int[] rowNum) {
        for (int i = 0; i < 1000; i++) {
            rowNum[0]++;
        }
    }

    @Test
    public void test3(){
        List<String> strings = new LinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        List<String> strings1 = new LinkedList<>();
        strings1.add("1");
        List<String> collect = strings.stream().filter(strings1::contains).collect(Collectors.toList());
        System.out.println(collect);

        System.out.println(strings1.contains("1"));
    }

    @Test
    public void test4(){
        String str ="WP20201123180122100012\n" +
                "WP20201124163343100014\n" +
                "WP20201125201700100017\n" +
                "WP20201126092527100018\n" +
                "WP20201127122729100022\n" +
                "WP20201127143758100023\n" +
                "WP20201127182432100032\n" +
                "WP20201127182432100034\n" +
                "WP20201130140828100035\n" +
                "WP20201207192511100001\n" +
                "WP20201207200103100002\n" +
                "WP20201207202122100005\n" +
                "WP20201207202526100007\n" +
                "WP20201209200302100000\n" +
                "WP20201211191539100007\n" +
                "WP20201214184606100019\n" +
                "WP20201216111227100022\n" +
                "WP20201218140520100043";
        String w = str.replace("W", "\",\"W");
        System.out.println(w);
    }


}
