import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author huchonglin
 * @date 2020/12/11 15:50
 */
public class String测试 {
    @Test
    public void test(){
//        String a = "12345";
//        String b = "abcd";
//        String c = "abc d";
//        System.out.println(StringUtils.);
    }

    @Test
    public void test1() {
        LinkedList<Object> objects = new LinkedList<>();
        String s = String.valueOf(objects);
        System.out.println(s.length());
        System.out.println(StringUtils.isBlank(s));

        Object obj = new LinkedList<>();
        List<?> list = (List<?>) obj;
        System.out.println(list.size());
    }

    @Test
    public void test2() {
        System.out.println("test20211231221_123213121323_commonBoostBargainProcess_TimeUnLabel".length());
        System.out.println("last_process_57A3B5E71FD5BFBEF77A5028F32AC476_TimeUnLabel".length());
    }

    @Test
    public void test4() {
        String input =
                "share_platform_push_av-5076";
        String appId_ = input.replace("-", "_appId_");
        System.out.println("tag_"+ appId_);
        System.out.println("tag_"+ appId_ + "_callback");
    }
    @Test
    public void test5() {
        System.out.println("qunar.team.flight.userproduct.open.flight_supply_open_api.push2ota");
        System.out.println("10m>100");
        List<String> list = new LinkedList<>();
        list.add("804_share_platform_push_quotation");
        list.add("804_share_platform_push_av");
        list.add("541_share_platform_push_shop_multi_way");
        list.add("2820_share_platform_push_quotation");
        list.add("2820_share_platform_push_av");
        list.add("5076_share_platform_push_quotation");
        list.add("5076_share_platform_push_av");
        list.add("1849_share_platform_push_av");
        list.add("4482_share_platform_push_av");
        list.forEach(input -> {
            String substring = input.substring(input.indexOf("_") + 1);
            String appId = input.substring(0, input.indexOf("_"));
            String out = "tag_" + substring + "_appId_" + appId;
            System.out.println(out);
            String search = "common_push_process_" + input + "_FAILED_Count";
            System.out.println(search);
            System.out.println("http://qaeapi.corp.qunar.com/api/watcher/callback?alarmName="+ out
                    +"&watcherIndexPath=http://watcher.corp.qunar.com/dashboard/apps/metric?addAlarm=1%26metric="+search+"%26currentPage=1%26group=all_metrics%26env=prod%26path=qunar.flight.flightuser.open.flight_supply_open_api%26nodeType=app_code");
        });
    }

    @Test
    public void test21 () {
        String tag = "packageCode_train_pay_success_pop___activityCode_train_pay_success_lottery___cycleDays_7___cycleRatio";
        String cycleDays = StringUtils.substringBetween(tag, "cycleDays_", "___");
        System.out.println(cycleDays);
    }
}
