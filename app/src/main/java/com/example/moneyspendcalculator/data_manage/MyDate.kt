package com.example.moneyspendcalculator.data_manage

class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {

    val dateString: String
        get() {
            var monthStr = Integer.toString(month)
            if (month <= 9) {
                monthStr = "0$monthStr"
            }
            return "$dayOfMonth:$monthStr:$year"
        }

    companion object {
        fun isBigger(date1: MyDate, date2: MyDate): Boolean {
            return if (date1.year < date2.year) {
                false
            } else if (date1.year > date2.year) {
                true
            } else {
                if (date1.month < date2.month) {
                    false
                } else if (date1.month > date2.month) {
                    true
                } else {
                    if (date1.dayOfMonth < date2.dayOfMonth) {
                        false
                    } else if (date1.dayOfMonth > date2.dayOfMonth) {
                        true
                    } else {
                        true
                    }
                }
            }
        }
    }
}