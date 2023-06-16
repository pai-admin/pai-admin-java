package run.gocli.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
@Slf4j
public class DateUtil {
    /**
     * 获取当前时间
     * @return String
     */
    public static String getCurrentDateTime(String pattern, long time) {
        // 设置默认格式
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        Date day = new Date();
        if (time != 0) {
            day.setTime(time);
        }
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        return sdf.format(day);
    }

    /**
     * 获取当前时间戳
     * @return String
     */
    public static long getCurrentTimestamp() {
        return new Date().getTime();
    }

    /**
     * 某日期时间戳
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static long getDateTimestamp(String datetime) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(datetime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.getTimeInMillis();
        }catch (ParseException e){
            log.error(e.getMessage());
            return 0;
        }
    }

    /**
     * 获取距离今天多少天的时间
     * @param day 天
     * @param pattern 格式
     * @return String
     */
    public static String getDate(int day, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat(pattern).format(now.getTime());
    }

    /**
     * 获取前天的时间戳
     * @return
     */
    public static Long getYesterdayTime() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        return calendar.getTimeInMillis();
    }

    public static long getTimestampByOffsetDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getMouthStart() {
        Calendar calendar = Calendar.getInstance();
        return getTimestampByOffsetDay(-calendar.get(Calendar.DAY_OF_MONTH) + 1);
    }

    public static Long getMouthEnd() {
        Calendar calendar = Calendar.getInstance();
        return getTimestampByOffsetDay(calendar
                .getMaximum(Calendar.DAY_OF_MONTH)
                - calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static Long lastMouthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTimeInMillis();
    }

    public static Long lastMouthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTimeInMillis();
    }
}
