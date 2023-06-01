package com.example.moneyspendcalculator.statistics_display;

import com.example.moneyspendcalculator.R;
import com.example.moneyspendcalculator.data_manage.moneyIncomeType;
import com.example.moneyspendcalculator.data_manage.moneyOutcomeType;

public class IconsStorage {
    private static final int[] outcomeIconIds = {R.drawable.food_icon,R.drawable.housing_utilities_icin,
            R.drawable.transport_icon,R.drawable.alcohol_cigarettes_icon,
            R.drawable.health_protection_icon,R.drawable.clothing_icon,
            R.drawable.household_goods_icon, R.drawable.techinque_icon,
            R.drawable.entertainement_icon, R.drawable.connection_icon,
            R.drawable.restaurant_icon, R.drawable.travelling_icon,
            R.drawable.education_icon, R.drawable.other_icon};

    private static final int[] incomeIconIds = {R.drawable.salary_icon, R.drawable.business_icon,
            R.drawable.sell_icon, R.drawable.other_icon};


    public static int getOutcomeIconByType(moneyOutcomeType iconType){
        int iconId = iconType.ordinal();
        return  outcomeIconIds[iconId];
    }

    public static int getIncomeIconByType(moneyIncomeType iconType){
        int iconId = iconType.ordinal();
        return  incomeIconIds[iconId];
    }
}
