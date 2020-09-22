package com.thoughtworks.capability.gtb;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 * 场景：
 * a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 * b:而我们在当前时区(北京时区)使用系统
 * c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ZoneId LONDON_ZONE_ID = ZoneId.of("Europe/London");
    private static final ZoneId CHICAGO_ZONE_ID = ZoneId.of("America/Chicago");

    public static void main(String[] args) {
        String timeStr = "2020-04-01 14:30:00";

        // 根据格式创建格式化类
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        // 从字符串解析得到会议时间
        LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
        ZonedDateTime zonedLondonTime = ZonedDateTime.of(meetingTime, LONDON_ZONE_ID);
        meetingTime = zonedLondonTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(meetingTime)) {
            LocalDateTime tomorrow = now.plusDays(1);
            Period period = Period.between(meetingTime.toLocalDate(), tomorrow.toLocalDate());
            // 格式化新会议时间
            meetingTime = LocalDateTime.from(period.addTo(meetingTime));
            ZonedDateTime zonedDateDefaultTime = ZonedDateTime.of(meetingTime, ZoneId.systemDefault());
            // 将添加好的新时间转换为【芝加哥】时间
            String showTimeStr = formatter.format(zonedDateDefaultTime.withZoneSameInstant(CHICAGO_ZONE_ID).toLocalDateTime());;
            System.out.println(showTimeStr);
        } else {
            System.out.println("会议还没开始呢");
        }
    }
}
