package com.summary.zkhdsummary.utils;

public class FormatDuring {

    /**
     *
     * @param 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long day = mss / (1000 * 60 * 60 * 24);
        long hour = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minute = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long second = (mss % (1000 * 60)) / 1000;
        if (hour==0 && minute==0 &&day==0){
            return +second+"秒前";
        }else if (day==0 && hour==0){
            return minute+"分钟"+second+"秒前";
        }else if (day==0 && minute==0){
            return hour+"小时";
        }else if (day==0){
            return hour+"小时"+minute+"分钟"+second+"秒前";
        }else if (hour==0){
            return day+"天"+minute+"分钟"+second+"秒前";
        }else if (minute==0){
            return day+"天"+hour+"小时"+second+"秒前";
        }
        return day+"天"+hour+"小时"+minute+"分钟"+second+"秒前";
    }

}
