package ru.creditnet.test;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by scan on 09.10.15.
 */
public class SimpleDateFormatFactory {

    private TimeZone UTC = TimeZone.getTimeZone("UTC");

    public SimpleDateFormat getInternalDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        sdf.setTimeZone(UTC);
        return sdf;
    }

    public SimpleDateFormat getExternalDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(UTC);
        return sdf;
    }

}
