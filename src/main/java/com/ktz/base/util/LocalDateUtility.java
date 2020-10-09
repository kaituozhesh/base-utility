package com.ktz.base.util;

import com.ktz.base.util.adjuster.LocalDateAdjuster;
import com.ktz.base.util.format.DateTimeFormat;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName LocalDateUtility
 * @Description Java8时间工具类
 * @Author 开拓者-骚豪
 * @Date 2020/10/9 8:51
 * @Version V1.0.0
 **/
public final class LocalDateUtility {

    private LocalDateUtility() {
    }

    /**
     * 默认月份格式化
     */
    private static final String DEFAULT_MONTH_PATTERN = "yyyyMM";

    /**
     * 生成当前月分前limit个月分的String字符串
     *
     * @param limit 生成的个数
     * @return 多个月分之间已`,`分割
     */
    public static String generateLimitString(int limit) {
        return String.join(",", generateLimitList(LocalDate.now(), limit, DEFAULT_MONTH_PATTERN));
    }

    /**
     * 生成localDate前limit个月分的String字符串
     *
     * @param localDate 传入日期
     * @param limit     生成的个数
     * @return 多个月分之间已`,`分割
     */
    public static String generateLimitString(LocalDate localDate, int limit) {
        return String.join(",", generateLimitList(localDate, limit, DEFAULT_MONTH_PATTERN));
    }

    /**
     * 生成localDate前limit个指定格式月分的String字符串
     *
     * @param localDate 传入日期
     * @param limit     生成的个数
     * @param pattern   格式化
     * @return 多个月分之间已`,`分割
     */
    public static String generateLimitString(LocalDate localDate, int limit, String pattern) {
        return String.join(",", generateLimitList(localDate, limit, pattern));
    }

    /**
     * 根据当前的时间生成前limit个月分集合
     *
     * @param limit 生成的个数
     * @return 返回limit个格式化日期List
     */
    public static List<String> generateLimitList(int limit) {
        return generateLimitList(LocalDate.now(), limit, DEFAULT_MONTH_PATTERN);
    }

    /**
     * 根据localDate的时间生成前limit个月分集合
     *
     * @param localDate 传入时间
     * @param limit     生成的个数
     * @return 返回limit个格式化日期List
     */
    public static List<String> generateLimitList(LocalDate localDate, int limit) {
        return generateLimitList(localDate, limit, DEFAULT_MONTH_PATTERN);
    }

    /**
     * 根据传入的时间生成前limit个格式化后的日期集合
     *
     * @param localDate 时间
     * @param limit     生成的个数
     * @param pattern   格式化
     * @return 返回limit个格式化日期List
     */
    public static List<String> generateLimitList(LocalDate localDate, int limit, String pattern) {
        AtomicInteger count = new AtomicInteger(1);
        return Stream.generate(() -> localDate.plusMonths(count.getAndIncrement()).format(DateTimeFormatter.ofPattern(pattern)))
                .limit(limit).collect(Collectors.toList());
    }

    /**
     * 获取当月第一天
     *
     * @return LocalDate 当月第一天
     */
    public static LocalDate firstDayOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取当月最后一天
     *
     * @return LocalDate 当月最后一天
     */
    public static LocalDate lastDayOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取下个月第一天
     *
     * @return LocalDate 下个月第一天
     */
    public static LocalDate firstDayOfNextMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    /**
     * 今年第一天
     *
     * @return LocalDate 今年第一天
     */
    public static LocalDate firstDayOfYear() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 今年最后一天
     *
     * @return LocalDate 今年最后一天
     */
    public static LocalDate lastDayOfYear() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 明年第一天
     *
     * @return LocalDate 明年第一天
     */
    public static LocalDate firstDayOfNextYear() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear());
    }

    /**
     * 获取下一个工作日
     *
     * @return 下一个工作日
     */
    public static LocalDate nextWorkingDay() {
        return LocalDate.now().with(LocalDateAdjuster.nextWorkingDay());
    }

    /**
     * 返回本月的第一个星期dayOfWeek
     * 如：firstInMonth(DayOfWeek.MONDAY)返回本月第一个星期一的日期
     *
     * @param dayOfWeek 星期
     * @return
     */
    public static LocalDate firstInMonth(DayOfWeek dayOfWeek) {
        return LocalDate.now().with(TemporalAdjusters.firstInMonth(dayOfWeek));
    }

    /**
     * 返回本月的最后一个星期dayOfWeek
     * 如：firstInMonth(DayOfWeek.MONDAY)返回本月最后一个星期一的日期
     *
     * @param dayOfWeek 星期
     * @return
     */
    public static LocalDate lastInMonth(DayOfWeek dayOfWeek) {
        return LocalDate.now().with(TemporalAdjusters.lastInMonth(dayOfWeek));
    }

    /**
     * 当天开始时间
     *
     * @return 返回当天开始时间 2020-10-09T00:00
     */
    public static LocalDateTime firstTimeOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 当天开始时间字符串形式
     *
     * @return 返回当天开始时间 2020-10-09 00:00:00
     */
    public static String formatFirstTimeOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(DateTimeFormat.ISO_LOCAL_DATE_TIME);
    }

    /**
     * 当天结束时间
     *
     * @return 返回当天结束时间 2020-10-09T23:59:59.999999999
     */
    public static LocalDateTime lastTimeOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 当天结束时间字符串形式
     *
     * @return 返回当天结束时间 2020-10-09 23:59:59.999999999
     */
    public static String formatLastTimeOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).format(DateTimeFormat.ISO_LOCAL_DATE_TIME);
    }

    /**
     * LocalDate -> Date
     *
     * @param localDate 待转换时间
     * @return 返回转换后的Date类型时间
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime 待转换时间
     * @return 返回转换后的Date类型时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
    }

    /**
     * Date -> LocalDate
     *
     * @param date 待转换时间
     * @return 返回转换后的LocalDate类型时间
     */
    public static LocalDate dataToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date 待转换时间
     * @return 返回转换后的LocalDateTime类型时间
     */
    public static LocalDateTime dataToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * LocalDate -> 时间戳
     *
     * @param localDate 待转换时间
     * @return 返回时间戳
     */
    public static long localDateToLong(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime -> 时间戳
     *
     * @param localDateTime 待转换时间
     * @return 返回时间戳
     */
    public static long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 时间戳转换成LocalDate
     *
     * @param timestamp 时间戳
     * @return 返回转换后的LocalDate
     */
    public static LocalDate longToLocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 时间戳转换成LocalDateTime
     *
     * @param timestamp 时间戳
     * @return 返回转换后的LocalDateTime
     */
    public static LocalDateTime longToLocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

}
