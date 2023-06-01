package com.example.moneyspendcalculator.data_manage;

import com.google.gson.internal.LinkedTreeMap;

public class moneyIncome {
    private moneyIncomeType type;
    public moneyIncomeType getType(){
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

    public moneyIncome(moneyIncomeType type, float value, MyDate date){
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public static moneyIncome fromLinkedTreeMap(LinkedTreeMap<String, Object> linkedTreeMap) {
        moneyIncomeType type = (moneyIncomeType) linkedTreeMap.get("type");
        float value = (Float) linkedTreeMap.get("value");
        MyDate date = (MyDate) linkedTreeMap.get("date");

        return new moneyIncome(type, value, date);
    }
}
