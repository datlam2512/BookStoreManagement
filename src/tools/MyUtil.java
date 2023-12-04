/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * 
 */
public class MyUtil {
    public static final Scanner sc = new Scanner(System.in);
    public static final Scanner sc2 = new Scanner(System.in);
    //
    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }
    //doc 1 chuoi co do dai min, max
    public static String readString(String message, int minL, int maxL) {
        if (minL>maxL) {
            int t=minL; minL = maxL; maxL = t;
        }
        String input = "";
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            int L = input.length();
            if (L < minL || L > maxL) OK = false;
        }
        while (!OK);
        return input;
    }
    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean OK;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            OK = validStr(input, pattern);
        }
        while (!OK);
        return input;
    }
    //doc 1 double >= min
    public static double readDouble(String message, double min) {
        String input = "";
        double result;
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine();
            result = Double.parseDouble(input.trim());
            if (result<min) OK = false;
        }
        while (!OK);
        return result;
    }
    //doc 1 int >= min
    public static int readInt(String message, int min) {
        String input = "";
        int result;
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine();
            result = Integer.parseInt(input.trim());
            if (result<min) OK = false;
        }
        while (!OK);
        return result;
    }
}
