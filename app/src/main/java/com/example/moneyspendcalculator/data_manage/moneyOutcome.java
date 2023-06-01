package com.example.moneyspendcalculator.data_manage;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Date;

public class moneyOutcome {
    private   moneyOutcomeType type;
    public moneyOutcomeType getType(){
        return  type;
    }

    private float value;
    public float getValue(){
        return value;
    }

    private MyDate date;
    public MyDate getDate(){
        return date;
    }

    public moneyOutcome(moneyOutcomeType type, float value, MyDate date){
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public static moneyOutcome fromLinkedTreeMap(LinkedTreeMap<String, Object> linkedTreeMap) {
        moneyOutcomeType type = (moneyOutcomeType) linkedTreeMap.get("type");
        float value = (Float) linkedTreeMap.get("value");
        MyDate date = (MyDate) linkedTreeMap.get("date");

        return new moneyOutcome(type, value, date);
    }
}




