package com.cxj.customviewstudy.utils;

public class PrintUtils {
    public static boolean Debug = true;
    public PrintUtils() {
    }
    public static void println(Object obj){
        if(Debug){
            System.out.println(obj == null ? null:obj);
        }
    }
    public static void println(Throwable e){
        if(Debug){
            if(e != null){
                e.printStackTrace();
            }
        }
    }

    public static void println(){
        System.out.println();
    }

    public static void print(Object obj){
        if(Debug){
            System.out.print(obj == null ? null:obj);
        }
    }
}
