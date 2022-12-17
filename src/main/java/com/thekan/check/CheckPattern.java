package com.thekan.check;

public class CheckPattern {
    public static int widthOfCheckInChar = 36;
    //public static String currency = "€";
    public static String currency = "$";
    public static String patternBody = "|%-5s%-12.11s%-8s%10s|";
    public static String patternInfo = "|%-10s%-16s%5s|";

    public static  String patternBody(String a, String b, String c, String d) {
        return String.format(patternBody, a, b, c, d);
    }
    public static  String patternInfo(String a, String b, String c) {
        return String.format(patternInfo, a, b, c);
    }
}
