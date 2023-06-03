package com.example.moneyspendcalculator.data_manage;

import android.content.Context;

import com.example.moneyspendcalculator.ObserverPattern.Observerable;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;


public class data_manager {


    public Observerable changedEvent = new Observerable();
    //constructor
    public data_manager(Context context){
        moneyOutcomeLoaderAndSaver =
                new DataLoaderAndSaver<moneyOutcome>(outcomeInfoSvaFileName,moneyOutcome.class
                        , context);
        moneyIncomeLoaderAndSaver =
                new DataLoaderAndSaver<moneyIncome>(incomeInfoSvaFileName,moneyIncome.class,
                        context);
    }

    public static data_manager manager;

    //on start init
    public static void Init(Context context){
        manager = new data_manager(context);

        manager.LoadData();
    }

    public static void AddIncome(moneyIncome income){
        if(manager == null) {
            throw  new NullPointerException("data_manager instance is not created");
        }
        manager.AddIncomeLocally(income);

    }

    private void AddIncomeLocally(moneyIncome income){
        incomeInfo.add(income);
        changedEvent.Invoke();
    }

    public static void AddOutcome(moneyOutcome outcome){
        if(manager == null) {
            throw  new NullPointerException("data_manager instance is not created");
        }
        manager.AddOutcomeLocally(outcome);
    }

    private void AddOutcomeLocally(moneyOutcome outcome){
        outcomeInfo.add(outcome);
        changedEvent.Invoke();
    }

    public Float getThisMonthRevenue(){
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

        float value = 0;
        for(moneyOutcome out : outcomeInfo){
            if(out.getDate().getMonth() == month && out.getDate().getYear() == year){
                value = out.getValue();
            }
        }
        for(moneyIncome in : incomeInfo){
            if(in.getDate().getMonth() == month&& in.getDate().getYear() == year){
                value += in.getValue();
            }
        }

        return  value;
    }

    public Float getPrevMonthRevenue(){
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        int year = currentDate.getYear();
        if(month < 0){
            month = 12;
            year--;
        }

        float value = 0;
        for(moneyOutcome out : outcomeInfo){
            if(out.getDate().getMonth() == month && out.getDate().getYear() == year){
                value += out.getValue();
            }
        }
        for(moneyIncome in : incomeInfo){
            if(in.getDate().getMonth() == month && in.getDate().getYear() == year){
                value += in.getValue();
            }
        }

        return  value;
    }

    public static void Destroy(){

        manager.SaveData();
    }





    public ArrayList<moneyOutcome> outcomeInfo;
    public ArrayList<moneyIncome> incomeInfo;

    ///loading and saving

    private DataLoaderAndSaver<moneyOutcome> moneyOutcomeLoaderAndSaver;
    private DataLoaderAndSaver<moneyIncome> moneyIncomeLoaderAndSaver;
    private static final String outcomeInfoSvaFileName = "outcomeData";
    private static final String incomeInfoSvaFileName = "incomeData";
    private void LoadData(){

        outcomeInfo = moneyOutcomeLoaderAndSaver.LoadData();
        if(outcomeInfo == null){
            outcomeInfo = new ArrayList<>();
        }
        incomeInfo = moneyIncomeLoaderAndSaver.LoadData();
        if(incomeInfo == null){
            incomeInfo = new ArrayList<>();
        }
    }

    private void SaveData(){
        moneyOutcomeLoaderAndSaver.SaveData(outcomeInfo.toArray(new moneyOutcome[0]));
        moneyIncomeLoaderAndSaver.SaveData(incomeInfo.toArray(new moneyIncome[0]));


    }
}
