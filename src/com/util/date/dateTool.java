package com.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: D丶Cheng
 * Date: 2017/6/18
 * Time: 21:21
 * Description:时间与字符串、时间戳与字符串相互转化的工具类
 */
public class dateTool {
    /**
     * 日期转换成字符串
     *
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
     *
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

    /**
     * 将字符串转为时间戳
     *
     * @param time
     * @return
     */
    public static String strToTimeStamp(String time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date;
        try {
            date = sdf.parse(time);
            long timeStamp = date.getTime();
            String str = String.valueOf(timeStamp);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }


    /**
     * 将时间戳转为字符串
     *
     * @param time
     * @return
     */
    public static String timeStampToStr(String time) {
        String re_StrTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        long timeStamp = Long.valueOf(time);
        re_StrTime = sdf.format(new Date(timeStamp * 1000L));
        return re_StrTime;
    }

    public static void main(String[] args) {
        /**** date与字符串互转 ****/
        //日期转字符串
        System.out.println(dateToStr(new Date()));
        //字符串转日期
        System.out.println(strToDate("2017-06-18 21:23:29"));

        /**** 时间戳与字符串互转 ****/
        String time = "2017年06月18日21时25分00秒";
        // 字符串转时间戳
        String re_str = strToTimeStamp(time);
        System.out.println(re_str);
        // 时间戳转字符串
        String data = timeStampToStr(re_str);
        System.out.println(data);
    }
}
