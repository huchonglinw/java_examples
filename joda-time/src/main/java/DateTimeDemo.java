import org.joda.time.*;
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
        System.out.println(dateTime);
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
        System.out.println(DateTime.now());
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
     * DateTime格式转换
     */
    @Test
    public void hotelTest1() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime); // 2021-09-06T11:06:38.233+08:00

        String pattern = "yyyy-MM-dd HH:mm:ss";
        System.out.println("pattern.length()：" + pattern.length()); // 19
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        String s = dateTime.toString(pattern);
        System.out.println(s);
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        System.out.println(localDateTime); // 2021-09-06T11:07:03.362

        LocalDate localDate = dateTime.toLocalDate();
        System.out.println(localDate); // 2021-09-06

        Date date = dateTime.toDate();
        LocalTime localTime = dateTime.toLocalTime();
        System.out.println(localTime);
        DateTime checkOut = new DateTime(2021, 7, 21, 11, 0, 0);
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


    @Test
    public void millsTest() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(JodaDateUtils.getMillis(l + ""));
        System.out.println((double)234234111);
    }

}
