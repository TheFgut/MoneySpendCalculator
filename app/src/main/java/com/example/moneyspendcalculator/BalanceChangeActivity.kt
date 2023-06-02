package com.example.moneyspendcalculator


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Spinner

import androidx.appcompat.app.AppCompatActivity
import com.example.moneyspendcalculator.data_manage.MoneyOperationType
import com.example.moneyspendcalculator.data_manage.MyDate
import com.example.moneyspendcalculator.data_manage.data_manager
import com.example.moneyspendcalculator.data_manage.moneyIncome
import com.example.moneyspendcalculator.data_manage.moneyIncomeType
import com.example.moneyspendcalculator.data_manage.moneyOutcome
import com.example.moneyspendcalculator.data_manage.moneyOutcomeType
import java.time.LocalDate


class BalanceChangeActivity : AppCompatActivity() {
    private var operationUnderTypeSpinner: Spinner? = null
    private var operationTypeSpinner: Spinner? = null
    private var editValueText:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.balance_change_action_settings)

        operationUnderTypeSpinner = findViewById<Spinner>(R.id.OperationUnderTypeSpinner)
        operationTypeSpinner = findViewById<Spinner>(R.id.operationTypeSpinner)
        editValueText = findViewById<EditText>(R.id.editTextNumberDecimal)

        setSpinnerValues(MoneyOperationType.values(), operationTypeSpinner)

        operationTypeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                operationTypeChanged(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // sometimes you need nothing here
            }
        }

        operationUnderTypeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                choosedOperationUndertype = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // sometimes you need nothing here
            }
        }

        val calendarView = findViewById<CalendarView>(R.id.choose_date)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateChanged(view,year,month,dayOfMonth)
        }

        val currentDate = LocalDate.now()
        updateDateInfo(currentDate.year,currentDate.monthValue,currentDate.dayOfMonth)

        operationTypeChanged(1)
    }

    private fun dateChanged(view:CalendarView, year:Int, month:Int, dayOfMonth:Int){
        updateDateInfo(year, month + 1, dayOfMonth)
    }
    //final settings
    private var choosedDate: MyDate = MyDate(0,0,0)
    private var choosedOperationType: MoneyOperationType = MoneyOperationType.Outcome
    private var choosedOperationUndertype: Int = 0
    private var value: Float = 0f

    private fun updateDateInfo(year:Int, month:Int, dayOfMonth:Int){
        choosedDate = MyDate(year,month, dayOfMonth)
    }

    //adding data
    fun addNewBalanceChange(view: View?) {
        var valueStr : String = editValueText?.getText().toString()
        if(valueStr != ""){
            value = valueStr.toFloat()
        }

        when(choosedOperationType){
            MoneyOperationType.Outcome ->{
                val outcome: moneyOutcome = moneyOutcome(
                    enumValues<moneyOutcomeType>().getOrNull(choosedOperationUndertype), -value,
                    choosedDate)
                Log.i("add", outcome.toString());
                data_manager.AddOutcome(outcome)
            }
            MoneyOperationType.Income ->{
                val income: moneyIncome = moneyIncome(
                    enumValues<moneyIncomeType>().getOrNull(choosedOperationUndertype), value,
                    choosedDate)
                data_manager.AddIncome(income)
            }
        }

        returnToMain(view)
    }

    fun returnToMain(view: View?) {
        finish()
    }

    fun operationTypeChanged(operationType: Int){
        choosedOperationUndertype = 0;
        if(operationType == 0){
            choosedOperationType = MoneyOperationType.Outcome;
            setSpinnerValues(moneyOutcomeType.values(), operationUnderTypeSpinner)
        }
        else{
            choosedOperationType = MoneyOperationType.Income;
            setSpinnerValues(moneyIncomeType.values(), operationUnderTypeSpinner)
        }
    }

    fun <T> setSpinnerValues(arr:Array<T>, spinner:Spinner?){
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, arr)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.setAdapter(adapter)

    }
}