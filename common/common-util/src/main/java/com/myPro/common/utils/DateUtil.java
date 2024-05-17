package com.myPro.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 处理日期的工具类
 */
public class DateUtil {
    /**
     * 判断date1是否是date2的前一天
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 如果date1是date2的前一天，返回true；否则返回false
     */
    public static boolean isPreviousDay(Date date1, Date date2) {
        // 检查date1是否比date2早一天
        long diff = getDateDiff(date1, date2);
        return diff == 24L * 60 * 60 * 1000; // 一天有86400000毫秒
    }

    /**
     * 判断date1和date2是不是同一天
      * @param date1
     * @param date2
     * @return
     */
    public static boolean isCommonDay(Date date1,Date date2){
        return getDateDiff(date1, date2) == 0;
    }

    /**
     * 获取两个日期，去除时间后，日期毫秒数之差
     * @param date1
     * @param date2
     * @return
     */
    public static long getDateDiff(Date date1,Date date2){
        // 清除两个日期对象的时间部分，只保留日期部分
        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal1.setTime(date1);
        cal2.setTime(date2);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
       return cal2.getTimeInMillis() - cal1.getTimeInMillis();
    }

    /**
     * 清空date的时间部分
     * @param date 待清空的数据
     * @return 清空的日期
     */
    public static Date clearTime(Date date){
        date.setMinutes(0);
        date.setHours(0);
        date.setSeconds(0);
        return  date;
    }
}
