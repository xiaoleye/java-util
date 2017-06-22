package com.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: D丶Cheng
 * Date: 2017/6/18
 * Time: 21:21
 * Description:时间与字符串相互转化的工具类
 */
public class dateTool {
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String dateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        System.out.println(dateToStr(new Date()));
        System.out.println(strToDate("2017-06-18 21:23:29"));
    }
}
