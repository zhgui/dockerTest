/**
 *
 */
package com.shark.common.utils;

import org.springframework.context.MessageSource;

public class MathUtils {


    public static String mathRound(Double juli, double danwei,double defaultNum) {
    	 if(null !=juli){
    		if(juli<0){
    			return "未知";
    		}
         	if(juli>=0 && juli<1000){
         		return juli+"m";
         	}
         	if(juli>=1000){
         		juli = Math.round(juli/danwei)/defaultNum;
         		return juli+"km";
         	}
         }else{
        	 return "未知";
         }
		return "未知";
    }

}
