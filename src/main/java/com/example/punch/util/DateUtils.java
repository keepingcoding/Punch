package com.example.punch.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zzs
 * @date 2019/11/16 0:07
 */
public class DateUtils {

    private DateUtils() {
    }

    /** 格式化日期 **/
    public static String formatDate(Date date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    /** 解析日期 **/
    public static Date parseDate(String dateStr, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /** 解析日期时间 **/
    public static Date parseDateTime(String dateStr, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }
}
