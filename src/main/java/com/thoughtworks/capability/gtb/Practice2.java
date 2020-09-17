package com.thoughtworks.capability.gtb;

import java.time.LocalDate;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {

  private static final int FRIDAY_TO_NEXT_WORKDAYS = 3;
  private static final int SATURDAY_TO_NEXT_WORKDAYS = 2;
  private static final int DEFAULT_DAYS_OFFSET = 1;

  public static LocalDate getNextWorkDate(LocalDate date) {
    switch (date.getDayOfWeek()) {
      case FRIDAY:
        return date.plusDays(FRIDAY_TO_NEXT_WORKDAYS);
      case SATURDAY:
        return date.plusDays(SATURDAY_TO_NEXT_WORKDAYS);
      default:
        return date.plusDays(DEFAULT_DAYS_OFFSET);
    }
  }
}
