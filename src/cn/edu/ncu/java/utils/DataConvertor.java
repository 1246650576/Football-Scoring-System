package cn.edu.ncu.java.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class DataConvertor {
    /**
     * @Description: Timestamp转换为 LocalDate
     * @Name: converFromTime
     * @Param: [timestamp]
     * @Return: java.time.LocalDate
     */
    public static LocalDate converFromTime(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate();
    }
    /**
     * @Description: LocalDate 转换为 Timestamp
     * @Name: converFromLocal
     * @Param: [date]
     * @Return: java.sql.Timestamp
     */
    public static  Timestamp converFromLocal(LocalDate date){
        return new Timestamp(date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }
}
