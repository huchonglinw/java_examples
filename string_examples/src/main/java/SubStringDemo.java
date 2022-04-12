import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: chonglin.hu
 * @date: 2022/1/28 16:56
 */
public class SubStringDemo {

    @Test
    public void test2() {
        String s2 = "tag_class5_group4_student2appId_12321";
        String appId = s2.substring(s2.indexOf("_") + 1, s2.indexOf("appId"));
        System.out.println(appId); // _class5_group4_student2
        String appId_ = s2.substring(s2.lastIndexOf("_"));
        System.out.println(appId_);
        System.out.println(s2.substring(0, s2.indexOf("_")));
    }

    @Test
    public void test3() {
        String s = "packageCode_pac123___activityCode_act123___processCode_pro123___extensionCode_extensionCode123___?tag";
        String s2 = "pac123___act123___pro123___extensionCode123___?tag";
        String s1 = "packageCode_pac123_activityCode_act123_processCode_pro123_?tag";
        System.out.println(StringUtils.substringBetween(s1, "extensionCode_", "_"));
        System.out.println(StringUtils.substringAfterLast(s, "_"));
        System.out.println(Arrays.toString(StringUtils.substringsBetween(s2, "___", "___")));

    }

    /**
     * substringAfter 截取target之后的字符
     */
    @Test
    public void Test() {
        System.out.println(StringUtils.substringAfter(str, "?"));
    }
    String str = "http://apolloadmin.corp.qunar.com/apollo/amp/config/activityList&asd?zxqw?asdzxcqwezxcasd=asdzxcasdqwzxc&casas%213678123zxcasdj&";
    String str2 = "http://apolloadmin.corp.qunar.com/apollo/amp/config/activityList&asd?zxqw?asdzxcqwezxcasd=asdzxc=asdqwz=xc&casas%213678123zxcasdj&&";

    @Test
    public void Test2() {
        String[] split = StringUtils.split(str2, "=", 2);
        for (String s : split) {
            System.out.println(s);
        }
    }
}
