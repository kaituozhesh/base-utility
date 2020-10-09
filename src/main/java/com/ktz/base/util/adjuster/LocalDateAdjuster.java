package com.ktz.base.util.adjuster;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName LocalDateAdjuster
 * @Description 自定义时间调节器
 * @Author 开拓者-骚豪
 * @Date 2020/10/9 13:19
 * @Version V1.0.0
 **/
public class LocalDateAdjuster {

    /**
     * 调用with方法传入LocalDateUtility.nextWorkingDay()获取下一个工作日
     *
     * @return 下一个工作日
     */
    public static TemporalAdjuster nextWorkingDay() {
        return TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek d = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (d == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (d == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
    }
}
