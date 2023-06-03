package com.example.moneyspendcalculator.data_manage

import android.content.Context
import com.example.moneyspendcalculator.ObserverPattern.Observerable
import com.example.moneyspendcalculator.data_manage.moneyIncome
import com.example.moneyspendcalculator.data_manage.moneyOutcome
import java.time.LocalDate

class data_manager(context: Context?) {
    var changedEvent = Observerable()
    private fun AddIncomeLocally(income: moneyIncome) {
        incomeInfo!!.add(income)
        changedEvent.Invoke()
    }

    private fun AddOutcomeLocally(outcome: moneyOutcome) {
        outcomeInfo!!.add(outcome)
        changedEvent.Invoke()
    }

    val thisMonthRevenue: Float
        get() {
            val currentDate = LocalDate.now()
            val month = currentDate.monthValue
            val year = currentDate.year
            var value = 0f
            for (out in outcomeInfo!!) {
                if (out.date!!.month == month && out.date.year == year) {
                    value = out.value
                }
            }
            for (`in` in incomeInfo!!) {
                if (`in`.date!!.month == month && `in`.date.year == year) {
                    value += `in`.value
                }
            }
            return value
        }
    val prevMonthRevenue: Float
        get() {
            val currentDate = LocalDate.now()
            var month = currentDate.monthValue - 1
            var year = currentDate.year
            if (month < 0) {
                month = 12
                year--
            }
            var value = 0f
            for (out in outcomeInfo!!) {
                if (out.date!!.month == month && out.date.year == year) {
                    value += out.value
                }
            }
            for (`in` in incomeInfo!!) {
                if (`in`.date!!.month == month && `in`.date.year == year) {
                    value += `in`.value
                }
            }
            return value
        }
    var outcomeInfo: ArrayList<moneyOutcome>? = null
    var incomeInfo: ArrayList<moneyIncome>? = null

    ///loading and saving
    private val moneyOutcomeLoaderAndSaver: DataLoaderAndSaver<moneyOutcome>
    private val moneyIncomeLoaderAndSaver: DataLoaderAndSaver<moneyIncome>

    //constructor
    init {
        moneyOutcomeLoaderAndSaver = DataLoaderAndSaver(
            outcomeInfoSvaFileName, moneyOutcome::class.java, context!!
        )
        moneyIncomeLoaderAndSaver = DataLoaderAndSaver(
            incomeInfoSvaFileName, moneyIncome::class.java,
            context
        )
    }

    private fun LoadData() {
        outcomeInfo = moneyOutcomeLoaderAndSaver.LoadData()
        if (outcomeInfo == null) {
            outcomeInfo = ArrayList()
        }
        incomeInfo = moneyIncomeLoaderAndSaver.LoadData()
        if (incomeInfo == null) {
            incomeInfo = ArrayList()
        }
    }

    private fun SaveData() {
        moneyOutcomeLoaderAndSaver.SaveData(outcomeInfo!!.toTypedArray())
        moneyIncomeLoaderAndSaver.SaveData(incomeInfo!!.toTypedArray())
    }

    companion object {
        var manager: data_manager? = null

        //on start init
        fun Init(context: Context?) {
            manager = data_manager(context)
            manager!!.LoadData()
        }

        fun AddIncome(income: moneyIncome) {
            if (manager == null) {
                throw NullPointerException("data_manager instance is not created")
            }
            manager!!.AddIncomeLocally(income)
        }

        fun AddOutcome(outcome: moneyOutcome) {
            if (manager == null) {
                throw NullPointerException("data_manager instance is not created")
            }
            manager!!.AddOutcomeLocally(outcome)
        }

        fun Destroy() {
            manager!!.SaveData()
        }

        private const val outcomeInfoSvaFileName = "outcomeData"
        private const val incomeInfoSvaFileName = "incomeData"
    }
}