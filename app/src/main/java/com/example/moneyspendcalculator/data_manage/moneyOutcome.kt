package com.example.moneyspendcalculator.data_manage

import com.google.gson.internal.LinkedTreeMap

class moneyOutcome(val type: moneyOutcomeType?, val value: Float, val date: MyDate?) {

    companion object {
        fun fromLinkedTreeMap(linkedTreeMap: LinkedTreeMap<String?, Any?>): moneyOutcome {
            val type = linkedTreeMap["type"] as moneyOutcomeType?
            val value = linkedTreeMap["value"] as Float
            val date = linkedTreeMap["date"] as MyDate?
            return moneyOutcome(type, value, date)
        }
    }
}