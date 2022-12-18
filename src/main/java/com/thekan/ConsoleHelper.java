package com.thekan;

import java.io.*;
import java.util.ArrayList;

public class ConsoleHelper {
    public static void print(String s){
        System.out.println(s);
    }

    public static void writeToFile(String str, String path){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            bw.write(str);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<String> readToFile(String path) {
        ArrayList<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String s;
            while((s=br.readLine())!=null){
                strings.add(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
