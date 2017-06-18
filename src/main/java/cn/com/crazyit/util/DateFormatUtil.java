package cn.com.crazyit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/18.
 */
public class DateFormatUtil {

    public static Date format(String origin, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(origin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
