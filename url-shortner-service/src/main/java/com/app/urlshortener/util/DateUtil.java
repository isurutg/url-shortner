package com.app.urlshortener.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public Date getExpireDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
}