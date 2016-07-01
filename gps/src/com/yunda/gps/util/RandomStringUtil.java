package com.yunda.gps.util;


public class RandomStringUtil {
	public static String random(int length){//传入的字符串的长度
        StringBuilder builder = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            
            int r = (int) (Math.random()*3);
            int rn1=(int)(48+Math.random()*10);
            int rn2=(int)(65+Math.random()*26);
            int rn3=(int)(97+Math.random()*26);
            
            switch(r){
            case 0:   
                builder.append((char)rn1);
                break;
            case 1:
                builder.append((char)rn2);
                break;
            case 2:
                builder.append((char)rn3);
                break;
            }
        }
        return builder.toString();
    }    
}
