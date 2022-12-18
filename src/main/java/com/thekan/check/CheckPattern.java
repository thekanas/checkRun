package com.thekan.check;

import java.time.format.DateTimeFormatter;

public class CheckPattern {
    public static int widthOfCheckInChar = 36;
    public static String currency = "$";
    public static String patternBody = "| %-5s%-14.13s%-7s%8s |";
    public static String patternInfoOneColumn = "| %-34s |";
    public static String patternInfoTwoColumn = "| %-18s%16s |";
    public static String patternForSeparators = "|%-36s|";
    public static String lineBreakCharacter = "\r\n";
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/M/yy");
    public static DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm:ss");


    public static  String patternBody(String a, String b, String c, String d) {
        return String.format(patternBody, a, b, c, d);
    }

    public static  String patternInfo(String a) {
        return String.format(patternInfoOneColumn, a);
    }

    public static  String patternInfo(String a, String b) {
        return String.format(patternInfoTwoColumn, a, b);
    }

    public static  String patternInfoCenter(String a) {
        String center = center(a, widthOfCheckInChar);
        return String.format(patternInfoOneColumn, center);
    }

    public static  String patternSeparators(String a) {
        return String.format(patternForSeparators, a);
    }

    public static String center (String str, int size) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pads/2; i++) {
            sb.append(" ");
        }
        sb.append(str);
        return sb.toString();
    }

}
