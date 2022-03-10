package tests;

import data.Color;

public class testColor {
    public static void run(){
        for(int i = 0; i<7; i++){
            System.out.println(Color.fromInt(i));
        }
    }
}
