import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * format
 * @author: chonglin.hu
 * @date: 2021/12/20 15:03
 */
public class 格式化demo {
    /**
     * last_abcsadaxzc_4_TimeUnLabel
     * last_abcsadaxzc_5_TimeUnLabel
     * last_abcsadaxzc_6_TimeUnLabel
     * last_abcsadaxzc_7_TimeUnLabel
     * last_abcsadaxzc_8_TimeUnLabel
     * last_abcsadaxzc_9_TimeUnLabel
     */
    @Test
    public void test() {
        String labelCodeTemplate = "last_" + "abcsadaxzc" + "_%s" + "_TimeUnLabel";
        List<String> labelCodeList = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            String labelCode = String.format(labelCodeTemplate, i);
            labelCodeList.add(labelCode);
        }

        labelCodeList.forEach(System.out::println);
    }

    /**
     * last_abcsadaxzc_0_TimeUnLabel
     * last_abcsadaxzc_1_TimeUnLabel
     * last_abcsadaxzc_2_TimeUnLabel
     */
    @Test
    public void tes2t() {
        String labelCodeTemplate = "last_" + "abcsadaxzc" + "_%s" + "_TimeUnLabel";
        List<String> labelCodeList = Lists.newLinkedList();
        for (int i = 0; i < 3; i++) {
            labelCodeList.add(String.format(labelCodeTemplate, i));
        }

        labelCodeList.forEach(System.out::println);
    }

    /**
     * 测试printf格式化代码
     */
    @Test
    public void test3() {
        String tem = " hello,%s,%s!";
        System.out.printf((tem) + "%n", "tom", "jack");
    }

    /**
     * 测试format参数为null
     */
    @Test
    public void te2st3() {
        String format = String.format("%s%s", null, "");
        System.out.println(format);
    }
}
