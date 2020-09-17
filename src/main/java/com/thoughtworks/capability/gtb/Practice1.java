package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.Month;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

    private static final Month MONTH_OF_LABOR_DAY = Month.MAY;
    private static final int DAY_OF_LABOR_DAY = 1;
    private static final int OFFSET_YEAR = 1;

    public static long getDaysBetweenNextLaborDay(LocalDate date) {
        LocalDate laborDayOfThisYear = LocalDate.of(date.getYear(), MONTH_OF_LABOR_DAY, DAY_OF_LABOR_DAY);
        if (date.isAfter(laborDayOfThisYear)) {
            laborDayOfThisYear = laborDayOfThisYear.plusYears(OFFSET_YEAR);
        }
        return (laborDayOfThisYear.toEpochDay() - date.toEpochDay());
    }
}
