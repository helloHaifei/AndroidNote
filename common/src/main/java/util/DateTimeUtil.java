package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/***
 * <p>时间格式转换</p>
 */
public class DateTimeUtil {
    private static final String TAG = "DateTimeUtil";

    /**
     * 将当前时间转换成字符串格式。
     * @param dateType 时间显示格式
     * @return
     */
    public static String getCurrentTime(String dateType) {
        return getCurrentTime(System.currentTimeMillis(), dateType);
    }

    /**
     * 将当前时间转换成字符串格式。
     * @param currentTime long型时间值
     * @param dateType 时间显示格式
     * @return
     */
    public static String getCurrentTime(long currentTime, String dateType) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dateType);
        Date date = new Date(currentTime);
        String strDate = sDateFormat.format(date);
        return strDate;
    }




    /**
     * 将日期时间字符串转化为Date对象
     *
     * @param dateStr
     * @return
     */
    public static Date convertStrToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期时间字符串转化为Date对象
     *
     * @param dateStr
     * @return
     */
    public static Date convertStrToDateForTFmt(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将传入时间与当前时间进行对比，是否今天昨天
     */
    public static String getTime(Date date, String todaySDF,
                                 String yesterDaySDF, String otherSDF) {
        SimpleDateFormat sfd = null;
        String time = "";
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        Date now = new Date();
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(now);
        targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
        targetCalendar.set(Calendar.MINUTE, 0);
        if (dateCalendar.after(targetCalendar)) {
            sfd = new SimpleDateFormat(todaySDF);
            time = sfd.format(date);
            return time;
        } else {
            if (dateCalendar.before(targetCalendar)
                    && dateCalendar.get(Calendar.YEAR) == targetCalendar
                    .get(Calendar.YEAR)) {
                sfd = new SimpleDateFormat(yesterDaySDF);
                time = sfd.format(date);
                return time;
            }
        }
        sfd = new SimpleDateFormat(otherSDF);
        time = sfd.format(date);
        return time;
    }

    /**
     * 通知页面的时间计算
     **/
    public static String getDateDistanceToNowNotify(Date startDate){
        if (startDate == null){
            return "未知";
        }
        return twoDateDistanceNotify(startDate, new Date(System.currentTimeMillis()));
    }

    /**
     * 通知页面计算两个日期型的时间相差多少时间
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static String twoDateDistanceNotify(Date startDate, Date endDate) {
        String date;
        if (startDate == null || endDate == null) {
            return null;
        }
        long timeLong = endDate.getTime() - startDate.getTime();
         if (timeLong <60 * 60 * 24 * 1000) {
             SimpleDateFormat sdfDay = new SimpleDateFormat(" a hh:mm", Locale.CHINA);
             if(sdfDay.format(startDate).indexOf("PM")!=-1){
                 date = sdfDay.format(startDate).replaceAll("PM", "下午");
             }else{
                 date = sdfDay.format(startDate).replaceAll("AM", "上午");
             }
             return date;
        }else if(timeLong >60 * 60 * 24 * 1000&&timeLong<60 * 60 * 24 * 1000*2){
             SimpleDateFormat sdfTwoDay = new SimpleDateFormat(" a hh:mm",Locale.CHINA);
             if(sdfTwoDay.format(startDate).indexOf("PM")!=-1){
                 date = sdfTwoDay.format(startDate).replaceAll("PM", "下午");
             }else{
                 date = sdfTwoDay.format(startDate).replaceAll("AM", "上午");
             }
             return "昨天"+date;
         }
         else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            if (sdf.format(startDate).equals(sdf.format(endDate))) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日 a hh:mm",Locale.CHINA);
                if(sdf1.format(startDate).indexOf("PM")!=-1){
                    date = sdf1.format(startDate).replaceAll("PM", "下午");
                }else{
                    date = sdf1.format(startDate).replaceAll("AM", "上午");
                }
                return date;
            } else {
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年-MM月-dd日 a hh:mm",Locale.CHINA);
                if(sdf2.format(startDate).indexOf("PM")!=-1){
                    date = sdf2.format(startDate).replaceAll("PM", "下午");
                }else{
                    date = sdf2.format(startDate).replaceAll("AM", "上午");
                }
                return date;
            }
        }
    }


}
