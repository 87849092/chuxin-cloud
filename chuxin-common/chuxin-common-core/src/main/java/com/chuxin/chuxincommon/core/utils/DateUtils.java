package com.chuxin.chuxincommon.core.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 初心
 * @create 2021-08-27 15:47
 */
public class DateUtils {
    /**
     * 显示年月日时分秒，例如 2015-08-11 09:51:53.
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 仅显示年月日，例如 2015-08-11.
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 仅显示时分秒，例如 09:51:53.
     */
    public static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * 显示年月日时分秒(无符号)，例如 20150811095153.
     */
    public static final String UNSIGNED_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 仅显示年月日(无符号)，例如 20150811.
     */
    public static final String UNSIGNED_DATE_PATTERN = "yyyyMMdd";

    /**
     * 返回当前的日期
     *
     * @return java.time.LocalDate
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     *
     * @return java.time.LocalTime
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     *
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间距离0点的秒数
     *
     * @return java.lang.long
     */
    public static long getSecondsToZero() {
        return chronoUnitSeconds(LocalDateTime.now(), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }

    /**
     * 根据两个时间获取两个时间相差多少秒数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitSeconds(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    /**
     * 根据两个时间获取两个时间相差多少小时
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitHours(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * 根据两个时间获取两个时间相差多少天
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitDay(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.DAYS.between(startTime, endTime);
    }

    /**
     * 根据两个时间获取两个时间相差多少周
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitWeeks(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.WEEKS.between(startTime, endTime);
    }

    /**
     * 根据两个时间获取两个时间相差多少天
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitMonths(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.MONTHS.between(startTime, endTime);
    }

    /**
     * 根据两个时间获取两个时间相差多少天
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return java.lang.long
     */
    public static long chronoUnitYears(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.YEARS.between(startTime, endTime);
    }

    /**
     * 格式化年月日为字符串
     *
     * @param localDate 传入需要格式化的日期
     * @param pattern   需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateToString(LocalDate localDate, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateFormat(localDate, dateTimeFormatter);

    }

    /**
     * 格式化年月日时分秒为字符串
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateTimeFormat(localDateTime, dateTimeFormatter);

    }

    /**
     * LocalDate格式化日期为日期格式
     *
     * @param localDate 传入需要格式化的日期
     * @param pattern   需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static LocalDate formatterStringToLocalDate(String localDate, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateParse(localDate, dateTimeFormatter);

    }

    /**
     * localDateTime格式化日期为日期格式
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static LocalDateTime formatterStringToLocalDateTime(String localDateTime, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateTimeParse(localDateTime, dateTimeFormatter);
    }

    /**
     * 获取LocalDate按照要求转化为字符串
     *
     * @param localDate         需要转化的数据
     * @param dateTimeFormatter 转化的格式
     * @return 转化后返回字符串
     */
    private static String getLocalDateFormat(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 获取LocalDateTime按照要求转化为字符串
     *
     * @param localDateTime     需要转化的数据
     * @param dateTimeFormatter 转化的格式
     * @return 返回结果
     */
    private static String getLocalDateTimeFormat(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取LocalDate按要求转化为字符串
     *
     * @param formatterDateTime 需要转化的数据
     * @param dateTimeFormatter 格式化
     * @return LocalDate
     */
    private static LocalDate getLocalDateParse(String formatterDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDate.parse(formatterDateTime, dateTimeFormatter);
    }

    /**
     * 获取格式化的结果
     *
     * @param pattern 传入的格式
     * @return 返回格式化结果
     */
    private static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取格式化LocalDateTime结果
     *
     * @param localDateTime     传入的数据
     * @param dateTimeFormatter 格式化的结果
     * @return 返回字符串
     */
    private static String getLocalDateTimeString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 获取localDateTime按照国际标准转化的值
     *
     * @param localDateTime     需要转化的数据
     * @param dateTimeFormatter 格式化
     * @return 返回 LocalDateTime
     */
    private static LocalDateTime getLocalDateTimeParse(String localDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    /**
     * 本周开始时间
     *
     * @return 返回本周开始时间
     */
    private static TemporalAdjuster getFirstTemporalAdjuster() {
        return TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
    }

    /**
     * 本周结束时间
     *
     * @return 返回本周结束时间
     */
    private static TemporalAdjuster getLastTemporalAdjuster() {
        return TemporalAdjusters.ofDateAdjuster(
                localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
    }

    /**
     * 获取某天的结束时间
     *
     * @return 天的结束时间  ZoneId.systemDefault()系统默认时区，如果需要改变时区使用ZoneId.of("时区")
     */
    public static LocalDateTime getTodayEndTime() {
        return LocalDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.MIDNIGHT);

    }

    /**
     * 在日期上增加数个整天
     *
     * @param instant 输入日期
     * @param day     增加或减少的天数
     * @return 增加或减少后的日期
     */
    public static LocalDateTime addDay(Instant instant, int day) {
        LocalDateTime localDateAboutInstant = getLocalDateAboutInstant(instant);
        return localDateAboutInstant.plusDays(day);
    }

    /**
     * 在日期上增加/减少（负数）数个小时
     *
     * @param instant 输入时间
     * @param hour    增加/减少的小时数
     * @return 增加/减少小时后的日期
     */
    public static LocalDateTime addDateHour(Instant instant, int hour) {
        LocalDateTime localDateAboutInstant = getLocalDateAboutInstant(instant);
        return localDateAboutInstant.plusHours(hour);
    }

    /**
     * 在日期上增加/减少数个分钟
     *
     * @param instant 输入时间
     * @param minutes 增加/减少的分钟数
     * @return 增加/减少分钟后的日期
     */
    public static LocalDateTime addDateMinutes(Instant instant, int minutes) {
        LocalDateTime localDateAboutInstant = getLocalDateAboutInstant(instant);
        return localDateAboutInstant.plusMinutes(minutes);
    }

    /**
     * 获取instant 转化的日期格式
     *
     * @param instant DK8 代替Date使用的类
     * @return 时间格式
     */
    private static LocalDateTime getLocalDateAboutInstant(Instant instant) {
        return instantToLocalDateTime(instant);
    }

    /**
     * instant  转化为 LocalDateTime
     *
     * @param instant 输入的时间
     * @return LocalDateTime的日期类型
     */
    public static LocalDateTime instantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
