package com.example.moneyspendcalculator.data_manage

import com.google.gson.internal.LinkedTreeMap

class moneyIncome(val type: moneyIncomeType?, val value: Float, val date: MyDate?) {

    companion object {
        fun fromLinkedTreeMap(linkedTreeMap: LinkedTreeMap<String?, Any?>): moneyIncome {
            val type = linkedTreeMap["type"] as moneyIncomeType?
            val value = linkedTreeMap["value"] as Float
            val date = linkedTreeMap["date"] as MyDate?
            return moneyIncome(type, value, date)
        }
    }
}