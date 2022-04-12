import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * 
 * @author ysk
 */
public class JodaDateUtils {

    public static final String FORMAT_DEFAULT = "yyyy-MM-dd";
    public static final String FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_ALL_L = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String FORMAT_ALL_M = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_SMALL = "yyyyMMddHHmmss";
    public static final String FORMAT_DEFAULT_SMALL = "yyyyMMdd";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_TIME_HOUR = "HH";
    public static final String FORMAT_TIME_MINUTE = "mm";
    public static final String FORMAT_TIME_SECOND = "ss";
    public static final String FORMAT_CHINESE_REMOVE_YEAR = "MM月dd日";
    public static final String FORMAT_CHINESE_REMOVE_YEAR_SHORT = "M月d日";
    public static final String FORMAT_ONLY_DAY = "dd日";
    public static final String FORMAT_SMALL_NOT_SECOND = "yyyyMMddHHmm";
    public static final String FORMAT_TIME_HOUR_MINUTE = "HH:mm";
    public static final String FORMAT_TIME_HOUR_MINUTE_SMALL = "HHmm";
    public static final String FORMAT_DATE_MONTH_DAY = "MM-dd";
    public static final String FORMAT_TWENTY_FOUR_TIME_HOUR_MINUTE = "kk:mm";

    public static final DateTimeFormatter defaultFormatter = DateTimeFormat.forPattern(FORMAT_DEFAULT);
    public static final DateTimeFormatter defaultAllFormatter = DateTimeFormat.forPattern(FORMAT_ALL);
    public static final DateTimeFormatter defaultAllFormatter_l = DateTimeFormat.forPattern(FORMAT_ALL_L);
    public static final DateTimeFormatter defaultSMALLFormatter = DateTimeFormat.forPattern(FORMAT_DEFAULT_SMALL);
    public static final DateTimeFormatter SMALLFormatter = DateTimeFormat.forPattern(FORMAT_SMALL);
    public static final DateTimeFormatter defaultTimeFormatter = DateTimeFormat.forPattern(FORMAT_TIME_HOUR_MINUTE);
    private static String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六","周日"};
    /**
     * 获取当前日期是周几<br>
     *  周一是第一天，周日是第七天
     * @return 当前日期是周几
     */
    public static String getWeekOfDate(String dateStr, String pattern) {
        DateTime dateTime = parseDateTime(dateStr, DateTimeFormat.forPattern(pattern));
        if (dateTime == null) {
            return null;
        }
        int w = dateTime.getDayOfWeek()-1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getDefaultWeekOfDate(String dateStr) {
        DateTime dateTime = parseDateTime(dateStr, defaultFormatter);
        if (dateTime == null) {
            return null;
        }
        int w = dateTime.getDayOfWeek()-1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 格式化日期
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    /**
     * 将指定的字符串转化成Date对象
     * 
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return parseDate(dateStr,dateTimeFormatter);
    }

    /**
     * 日期小于当前日期，则返回true
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isLessCurrentTime(String dateStr, String pattern){
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            String currentDateStr = formatDate(currentDate, pattern);
            return dateStr.compareTo(currentDateStr) < 0;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 将指定的字符串转换成Date对象，适用于重复适用同一个DateTimeFormatter对象的情况
     * 
     * @param dateStr
     * @param formatter
     * @return
     */
    public static Date parseDate(String dateStr, DateTimeFormatter formatter) {
        DateTime dateTime = parseDateTime(dateStr, formatter);
        if (dateTime == null) {
            return null;
        }
        return dateTime.toDate();
    }
    public static DateTime parseDateTime(String dateStr, DateTimeFormatter formatter) {
        if (formatter == null || StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return formatter.parseDateTime(dateStr);
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 获取指定的formatter对象，适用于重复适用同一个DateTimeFormatter对象的情况
     * 
     * @param pattern
     * @return
     */
    public static DateTimeFormatter getFormatter(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return null;
        }
        return DateTimeFormat.forPattern(pattern);
    }

    /**
     * 将hh:mm转换成总分钟数的形式 例如 12:00 -> 720
     * 
     * @param hour
     * @return
     */
    public static int convertHourToMin(String hour) {
        if (!hour.contains(":")) {
            return 0;
        }
        String[] strArr = hour.split(":");
        if (strArr.length != 2) {
            return 0;
        }
        String hourStr = strArr[0].trim();
        String minStr = strArr[1].trim();
        return Integer.parseInt(hourStr) * 60 + Integer.parseInt(minStr);
    }

    /**
     * 计算两个字符串表示的时间相差的分钟数
     * 
     * @param startDate
     * @param endDate
     * @param format 日期时间格式
     * @return
     */
    public static long getIntervalInMinutes(String startDate, String endDate, String format) {
        Date bDate = JodaDateUtils.parseDate(startDate, format);
        Date eDate = JodaDateUtils.parseDate(endDate, format);
        if (bDate == null || eDate == null) {
            return 0L;
        }
        long ei = eDate.getTime() - bDate.getTime();
        long minutes = ei / (1000 * 60);
        return minutes;
    }

    /**
     * 获取指定的分钟数中包含的小时数
     * 
     * @param minutes
     * @return
     */
    public static long getHour(long minutes) {
        return minutes / 60;
    }

    /**
     * 除去整数小时之后剩余的分钟数
     * 
     * @param mimutes
     * @return
     */
    public static long getMinutes(long mimutes) {
        return mimutes % 60;
    }
    
    
    public static int compareTime(String beforeTime,String afterTime){//18:00,19:00
		if(beforeTime.length()<6 && afterTime.length()<6){
			int btime = Integer.parseInt(beforeTime.split(":")[0]);
			int etime = Integer.parseInt(afterTime.split(":")[0]);
			if(btime >= etime){
				return 1;
			}else{
				return 0;
			}
		}
		return -1;
	}

    /**
     * 为指定的日期增加指定的天数
     * @param date
     * @param day
     * @return
     */
    public static Date addDays(Date date,int day){
    	DateTime dateTime = new DateTime(date);
    	dateTime = dateTime.plusDays(day);
    	return dateTime.toDate();
    }
    public static DateTime addDays(String dateStr,int day,DateTimeFormatter formatter){
        DateTime dateTime = parseDateTime(dateStr,formatter);
        if (dateTime == null) {
            return null;
        }
        return dateTime.plusDays(day);
    }
    public static String addDays4Str(String dateStr,int day,DateTimeFormatter formatter){
        DateTime dateTime = addDays(dateStr,day,formatter);
        if (dateTime != null) {
            return dateTime.toString(formatter);
        }
        return dateStr;
    }
    /**
     * 将传入的日期增加一天
     * @param dateStr
     * @param day
     * @param pattern
     * @return
     */
    public static String addDays(String dateStr, int day,String pattern){
    	Date date = parseDate(dateStr, pattern);
    	date = addDays(date, day);
    	return formatDate(date, pattern);
    }
    
    
    /**
     * 计算两个日期的时间间隔，以小时为单位
     * @param buyedFlightDate
     * @param buyingFlightDate
     * @return
     */
    public static long getIntervalInHoursWithSign(Date buyedFlightDate,Date buyingFlightDate){
    	 long interval = buyedFlightDate.getTime() - buyingFlightDate.getTime();
         long hour = interval / (1000 * 60 * 60);
         return hour;
    }
    
    /**
     * 为指定的日期增加指定的小时数
     * @param date
     * @param i
     * @return
     */
    public static Date addHour(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, i);
        return calendar.getTime();
    }
    /**
     * 计算到达日期
     * @param depDate
     * @param depTime
     * @param arrTime
     * @return
     */
    public static String getArrDate(String depDate,String depTime,String arrTime){
        if(StringUtils.isBlank(depDate) || StringUtils.isBlank(depTime) || StringUtils.isBlank(arrTime)){
            return "";
        }
        if(StringUtils.trim(arrTime).compareTo(StringUtils.trim(depTime)) < 0){
            return addDays(depDate, 1,JodaDateUtils.FORMAT_DEFAULT);
        }else{
            return depDate;
        }
    }


    public static boolean isDateInRange(DateTime dateTime,DateTime begin,DateTime end){
        if (dateTime.getMillis() == begin.getMillis() || dateTime.getMillis() == end.getMillis()) {
            return true;
        }
        if (dateTime.getMillis() > begin.getMillis() && dateTime.getMillis() < end.getMillis()) {
            return true;
        }

        return false;
    }

    public static int getDayNum(DateTime dateTime) {
        long millis = dateTime.getMillis();

        return (int) (millis/1000/60/60/24);
    }

    public static long getMillis(String operateTime){
        if (NumberUtils.isNumber(operateTime)) {
            return NumberUtils.toLong(operateTime);
        } else {
            DateTime dateTime = JodaDateUtils.parseDateTime(operateTime, JodaDateUtils.defaultAllFormatter);
            if (dateTime == null) {
                return -1;
            }
            return dateTime.getMillis();
        }
    }

    /**
     * 获取间隔的自然天数，只与日期有关
     * @return
     */
    public static int getIntervalNaturalDays(DateTime begin, DateTime end){
        DateTime startOfBeginDay = begin.withTimeAtStartOfDay();
        DateTime startOfEndDay = end.withTimeAtStartOfDay();
        Interval interval = new Interval(startOfBeginDay, startOfEndDay);
        Period period = interval.toPeriod();
        return period.getDays();
    }

    public static DateTime getAfterDayStart(int count){
        DateTime tomorrow = new DateTime().plusDays(count);
        DateTime tomorrowStart = new DateTime(tomorrow.getYear(),tomorrow.getMonthOfYear(),tomorrow.getDayOfMonth(),0,0,0);
        return tomorrowStart;
    }
    public static DateTime getAfterDayStart(DateTime baseTime,int count){
        DateTime tomorrow = baseTime.plusDays(count);
        DateTime tomorrowStart = new DateTime(tomorrow.getYear(),tomorrow.getMonthOfYear(),tomorrow.getDayOfMonth(),0,0,0);
        return tomorrowStart;
    }
    public static DateTime getAfterHourStart(int count){
        DateTime dateTime = new DateTime().plusHours(count);
        DateTime nextHourStart = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),dateTime.getHourOfDay(),0,0);
        return nextHourStart;
    }
    public static DateTime getAfterHourStart(DateTime baseTime,int count){
        DateTime dateTime = baseTime.plusHours(count);
        DateTime nextHourStart = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),dateTime.getHourOfDay(),0,0);
        return nextHourStart;
    }
    public static DateTime getAfterMinuteStart(int count){
        DateTime dateTime = new DateTime().plusMinutes(count);
        DateTime nextMinuteStart = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),dateTime.getHourOfDay(),dateTime.getMinuteOfHour(),0);
        return nextMinuteStart;
    }
    public static DateTime getAfterMinuteStart(DateTime baseTime,int count){
        DateTime dateTime = baseTime.plusMinutes(count);
        DateTime nextMinuteStart = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),dateTime.getHourOfDay(),dateTime.getMinuteOfHour(),0);
        return nextMinuteStart;
    }
    public static DateTime getAfterSecond(DateTime baseTime,int count){
        DateTime dateTime = baseTime.plusSeconds(count);
        DateTime nextSecondStart = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),dateTime.getHourOfDay(),dateTime.getMinuteOfHour(),dateTime.getSecondOfMinute());
        return nextSecondStart;
    }

    public static DateTime parseDateStr(DateTime baseTime,String dateStr, DateTime defaultDateTime){
        if (StringUtils.equalsIgnoreCase(dateStr,"today")) {
            return JodaDateUtils.getAfterDayStart(baseTime,1);
        } else if (StringUtils.endsWith(dateStr,"天")) {
            int num = NumberUtils.toInt(StringUtils.removeEnd(dateStr,"天"));
            return JodaDateUtils.getAfterDayStart(baseTime, num);
        } else if (StringUtils.endsWith(dateStr,"小时")) {
            int num = NumberUtils.toInt(StringUtils.removeEnd(dateStr,"小时"));
            return JodaDateUtils.getAfterHourStart(baseTime, num);
        } else if (StringUtils.endsWith(dateStr,"分钟")) {
            int num = NumberUtils.toInt(StringUtils.removeEnd(dateStr,"分钟"));
            return JodaDateUtils.getAfterMinuteStart(baseTime, num);
        } else if (StringUtils.endsWith(dateStr,"秒")) {
            int num = NumberUtils.toInt(StringUtils.removeEnd(dateStr,"秒"));
            return JodaDateUtils.getAfterSecond(baseTime, num);
        }
        if (NumberUtils.isNumber(dateStr)) {
            long mill = NumberUtils.toLong(dateStr);
            return new DateTime(mill);
        }
        DateTime dateTime = parseDateTime(dateStr, defaultAllFormatter);
        if (dateTime != null) {
            return dateTime;
        }
        return defaultDateTime;
    }
}
