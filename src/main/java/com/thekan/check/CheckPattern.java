package com.thekan.check;

import java.time.format.DateTimeFormatter;

public class CheckPattern {
    //путь для сохранения файла с чеком
    public static String patch = "check.txt";
    //ширина чека в символах
    public static int widthOfCheckInChar = 36; 
    public static String currency = "$";
    //шаблон для тела чека с товарами, колличесво символов(s и дополнительные пробелы, не включая начальные символы границы чека если они есть) 
    //должно соответствовать widthOfCheckInChar
    public static String patternBody = "| %-5s%-14.13s%-7s%8s |"; //шаблон для тела чека с товарами, 
    //шаблон для надписей на чеке в одну колонку
    public static String patternInfoOneColumn = "| %-34s |";
    //шаблон для надписей на чеке в две колонки
    public static String patternInfoTwoColumn = "| %-18s%16s |";
    //шаблон для строки-разделителя
    public static String patternForSeparators = "|%-36s|";
    //шаблон переноса строки
    public static String lineBreakCharacter = "\r\n";
    //формат времени и даты
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yy");
    public static DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");


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
        sb.append(" ".repeat(pads / 2));
        sb.append(str);
        return sb.toString();
    }

}
