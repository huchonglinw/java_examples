import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;

/**
 * @author: hcl
 * @date: 2021/7/20 18:33
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonthOfYear());

        dateTime = new DateTime(new Date());
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonthOfYear());
    }

    /**
     * 获取今天的起始时间
     */
    @Test
    public void test() {
        DateTime today = DateTime.now().withTimeAtStartOfDay();
        System.out.println(today);
    }

    /**
     * 计算酒店入住和离店时间
     */
    @Test
    public void hotelTest() {
        DateTime checkIn = new DateTime(2021, 7, 20, 14, 0, 0);
        DateTime checkOut = new DateTime(2021, 7, 21, 11, 0, 0);
        System.out.println(Hours.hoursBetween(checkIn, checkOut).getHours());
    }


    /**
     * 线程安全
     */
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void parseTest() {
        System.out.println(dateTimeFormatter.print(new DateTime()));
        System.out.println(dateTimeFormatter.parseDateTime("2021-07-21").toDateTime());
    }
}
