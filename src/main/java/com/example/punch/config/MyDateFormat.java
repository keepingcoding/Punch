package com.example.punch.config;

import java.text.*;
import java.util.Date;

/**
 * 处理jackson不能识别yyyy-MM-dd HH:mm:ss类似格式的数据
 * 默认只支持以下格式：
 *      "yyyy-MM-dd'T'HH:mm:ss.SSSZ"；
 *      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"；
 *      "yyyy-MM-dd";
 *      "EEE, dd MMM yyyy HH:mm:ss zzz";
 *      long类型的时间戳（毫秒时间戳）
 *
 * @author zzs
 * @date 2019/11/18 10:57
 */
public class MyDateFormat extends DateFormat {

    private DateFormat dateFormat;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    public MyDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;

        try {
            date = simpleDateFormat.parse(source, pos);
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    /** 重写日期格式化方法 **/
    @Override
    public Date parse(String source) throws ParseException {
        Date date = null;

        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
            date = dateFormat.parse(source);
        }

        return date;
    }

    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new MyDateFormat((DateFormat) format);
    }
}
