package com.example.anand.magnum_chat_app.Utils;

public class stringManipulation {

    public static String compress(String input){
        String rv=input.replace(' ','_');
        rv=rv.replace('.','-');
        return rv;
    }


    public static String expand(String input){
        return input.replace('_',' ');
    }
}
