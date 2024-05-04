package com.gtsoftware.despesas.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Calendar strToCalendar(String strDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(strDate);
        return new Calendar.Builder().setInstant(date).build();
    }
}
