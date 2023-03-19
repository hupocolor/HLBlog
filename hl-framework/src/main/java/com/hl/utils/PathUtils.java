package com.hl.utils;

import java.util.Calendar;
import java.util.UUID;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
public class PathUtils {
    public static String getFileOssPath(String fileName){
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        return year+"/"+month+"/"+uuid+fileType;
    }
}
