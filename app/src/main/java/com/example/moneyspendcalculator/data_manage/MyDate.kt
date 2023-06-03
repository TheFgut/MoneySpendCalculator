package com.example.moneyspendcalculator.data_manage;

import android.view.ViewDebug;

public class MyDate {
    private int year;

    public  int getYear(){
        return  year;
    }

    private int month;
    public  int getMonth(){
        return  month;
    }
    private int dayOfMonth;
    public  int getDayOfMonth(){
        return  dayOfMonth;
    }

    public MyDate(int year,int month,int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public String getDateString(){
        String monthStr = Integer.toString(month);
        if(month <= 9){
            monthStr = "0" + monthStr;
        }
        return dayOfMonth + ":" + monthStr + ":" + year;
    }

    public static boolean isBigger(MyDate date1, MyDate date2){
        if(date1.year < date2.year){
            return false;
        }
        else if(date1.year > date2.year){
            return true;
        }
        else {
            if(date1.month < date2.month){
                return false;
            }
            else if(date1.month > date2.month){
                return true;
            }
            else {
                if(date1.dayOfMonth < date2.dayOfMonth){
                    return false;
                }
                else if(date1.dayOfMonth > date2.dayOfMonth){
                    return true;
                }
                else {
                    return true;
                }
            }
        }
    }

}
