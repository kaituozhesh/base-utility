package com.ktz.base.util.format;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

/**
 * @ClassName DateTimeFormat
 * @Description 时间格式化
 * @Author 开拓者-骚豪
 * @Date 2020/10/9 14:08
 * @Version V1.0.0
 **/
public class DateTimeFormat {

    public static final DateTimeFormatter ISO_LOCAL_TIME;

    static {
        ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalStart()
                .appendFraction(NANO_OF_SECOND, 0, 9, true)
                .toFormatter();
    }

    public static final DateTimeFormatter ISO_LOCAL_DATE;

    static {
        ISO_LOCAL_DATE = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .optionalStart()
                .appendOffsetId()
                .toFormatter();
    }

    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME;

    static {
        ISO_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .append(ISO_LOCAL_DATE)
                .appendLiteral(" ")
                .append(ISO_LOCAL_TIME)
                .toFormatter();
    }

}
