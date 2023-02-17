package com.milk.job.common.utils;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-12 13:52
 */
public class FileUtils {

    // 图片允许的后缀扩展名
    public static String[] IMAGE_FILE_EXTD = new String[]{"png", "bmp", "jpg", "jpeg", "pdf"};

    public static boolean isFileAllowed(String fileName) {
        for (String ext : IMAGE_FILE_EXTD) {
            if (ext.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

}