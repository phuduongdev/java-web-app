/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author phudev
 */
public class DateTimeManager {

    public static java.sql.Date getSQLDate(java.time.LocalDate date) {
        return java.sql.Date.valueOf(date.toString());
    }

    public static long convertDateToMiliseconds(java.util.Date date) {
        return date.getTime();
    }

    public static String convertTimestampToStringDate(java.sql.Timestamp timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(timestamp);
    }
}
