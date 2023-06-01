package com.example.moneyspendcalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.moneyspendcalculator.ObserverPattern.Observer
import com.example.moneyspendcalculator.data_manage.data_manager


public class MainActivity: AppCompatActivity(), Observer {
    private var mounthMoneyStatusImage : ImageView? = null
    private var mounthRevenueTextDisp : TextView? = null
    private var mounthComperedToPrevMoneyStatusImage : ImageView? = null
    private var mounthComparedToPrevRevenueTextDisp : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data_manager.Init(this);
        data_manager.manager.changedEvent.addObserver(this)

        mounthMoneyStatusImage = findViewById<ImageView>(R.id.mounthMoneyStatusImageView)
        mounthRevenueTextDisp = findViewById<TextView>(R.id.mounthRevenueText)

        mounthComperedToPrevMoneyStatusImage =
            findViewById<ImageView>(R.id.prevMonthComparedMoneyStatusImageView)
        mounthComparedToPrevRevenueTextDisp =
            findViewById<TextView>(R.id.prevMounthComparedRevenueText)

        update();
    }

    fun goToAddNewBalanceChangeSetting(view: View?) {
        val intent = Intent(this, BalanceChangeActivity::class.java)
        startActivity(intent)
    }

    fun goToStats(view: View?) {
        val intent = Intent(this, StatsViewingActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        data_manager.Destroy();
        super.onDestroy()
    }

    public fun updateInfo(mounthRevenue: Float, prevMounthRevenue:Float) {
        mounthRevenueTextDisp?.setText("This month: "+ mounthRevenue.toString())
        setRevenueIcon(mounthRevenue,mounthMoneyStatusImage)


        val comparedRevenue = mounthRevenue - prevMounthRevenue
        mounthComparedToPrevRevenueTextDisp?.setText("Compared to prev month: "
                + comparedRevenue.toString())
        setRevenueIcon(comparedRevenue,mounthComperedToPrevMoneyStatusImage)

    }

    private fun setRevenueIcon( revenue: Float,imageV : ImageView? ){
        if(revenue < 0){
            imageV?.setImageResource(R.drawable.money_loss_month_status)
        }
        else if(revenue > 0){
            imageV?.setImageResource(R.drawable.money_recieve_month_status)
        }
        else{
            imageV?.setImageResource(R.drawable.neutral_money_status)
        }
    }

    override fun update() {
        var thisMonth: Float = data_manager.manager.thisMonthRevenue;
        var prevMonth: Float = data_manager.manager.prevMonthRevenue;
        updateInfo(thisMonth, prevMonth)
    }
}