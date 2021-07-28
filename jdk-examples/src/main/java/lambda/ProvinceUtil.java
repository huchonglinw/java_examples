package lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 省份工具类
 * @author huchonglin
 * @date 2021/1/18 14:40
 */
public class ProvinceUtil {
    private static final String[] CORRECTS = new String[]{
            "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省",
            "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "北京市", "天津市", "上海市", "重庆市",
            "香港特别行政区", "澳门特别行政区","我的省"};

    /**
     * 格式化省份名字
     */
    public static String format(String provinceName){
        if(StringUtils.isEmpty(provinceName)) { return provinceName;}

        AtomicInteger count = new AtomicInteger(0);
        AtomicReference<String> correctName = new AtomicReference<>();

        Arrays.stream(CORRECTS).parallel().forEach(current -> {
            if (current.contains(provinceName)) {
                final int increment = count.getAndIncrement();
                if(0 == increment) {
                    correctName.compareAndSet(null,current);
                    return;
                }
                if(!StringUtils.equals(null, correctName.get())) {
                    correctName.set(null);
                }
            }
        });

        return correctName.get();
    }

    public static String format1(String provinceName){
        if(StringUtils.isEmpty(provinceName)) { return provinceName;}

        AtomicInteger count = new AtomicInteger(0);
        AtomicReference<String> correctName = new AtomicReference<>("");

        Arrays.stream(CORRECTS).forEach(current -> {
            if (current.contains(provinceName)) {
                final int increment = count.getAndIncrement();
                if(0 == increment) {
                    correctName.compareAndSet("",current);
                    return;
                }
                if(!StringUtils.equals("", correctName.get())) {
                    correctName.set("");
                }
            }
        });

        return correctName.get();
    }

    @Test
    public void test(){
        final long start1 = System.currentTimeMillis();
        String result1 = format1("我");
        final long end1 = System.currentTimeMillis();

        final long start = System.currentTimeMillis();
        String result = format("我");
        final long end = System.currentTimeMillis();



        System.out.println("并发时间：" + (end - start));
        System.out.println("单线程时间：" + (end1 - start1));
        System.out.println(StringUtils.equals(null,result));
        System.out.println(StringUtils.equals("",result1));
        System.out.println("result： " +result);
    }

}
