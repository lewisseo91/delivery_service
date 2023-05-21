package com.delivery.order.util;

import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDateTime startOfDateBefore(LocalDateTime date, Integer beforeDate) {
        LocalDateTime nowDayBefore4 = LocalDateTime.now().minusDays(beforeDate);

        return nowDayBefore4.toLocalDate().atStartOfDay();
    }

    public static boolean isBeforeDateFromBaseDate(LocalDateTime baseDate, LocalDateTime date) {
        return date.isBefore(baseDate);
    }
}
