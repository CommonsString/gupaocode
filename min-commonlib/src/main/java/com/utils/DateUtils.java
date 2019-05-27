package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date toYY_mm_dd_String(Date now) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tmpe = format.format(new Date());
        Date time = null;
        try {
            time = format.parse(tmpe);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


}
