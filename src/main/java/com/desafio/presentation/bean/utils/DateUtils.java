package com.desafio.presentation.bean.utils;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private static final  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static LocalDate stringToLocalDate(String value) {
        return LocalDate.parse(value, dateTimeFormatter);
    }

    public static String localDateToString(LocalDate value) {
        if(value == null){
            return "";
        }
        var date = (LocalDate) value;
        return date.format(dateTimeFormatter);
    }

    public static Integer diffInYears(LocalDate initialDate, LocalDate finalDate){
        Period intervalPeriod = Period.between(initialDate, finalDate);
        return intervalPeriod.getYears();
    }
}
