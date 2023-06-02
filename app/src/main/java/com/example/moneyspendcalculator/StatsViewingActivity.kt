package com.example.moneyspendcalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.moneyspendcalculator.data_manage.MyDate
import com.example.moneyspendcalculator.data_manage.data_manager
import com.example.moneyspendcalculator.data_manage.moneyIncome
import com.example.moneyspendcalculator.data_manage.moneyOutcome
import com.example.moneyspendcalculator.statistics_display.IconsStorage


class StatsViewingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stats_activity)

        val  out = arrayOf<moneyOutcome>();

        showAndSortData(
            data_manager.manager.incomeInfo.toTypedArray(),
            data_manager.manager.outcomeInfo.toTypedArray())

        val button = findViewById<Button>(R.id.buttonBackFromStatsView)
        button.setOnClickListener {
finish()
        }
    }

    private fun showAndSortData(income: Array<moneyIncome>, outcome: Array<moneyOutcome>){

        var incomeNum = 0
        var outcomeNum = 0
        do {
            var incomeNumBiggerThanArraySize = (incomeNum >= income.size) or (income.isEmpty())
            var outcomeNumBiggerThanArraySize = (outcomeNum >= outcome.size)or (outcome.isEmpty())
            if(incomeNumBiggerThanArraySize or outcomeNumBiggerThanArraySize){
                if(!incomeNumBiggerThanArraySize){
                    addMoneyIncomeDisplay(income[incomeNum])
                    incomeNum++
                }
                else if(!outcomeNumBiggerThanArraySize){
                    addMoneyOutcomeDisplay(outcome[outcomeNum])
                    outcomeNum++
                }
                else{
                    break
                }

            }
            else{
                var incomeDate = income[incomeNum].date
                var outcomeDate = outcome[outcomeNum].date
                if(MyDate.isBigger(incomeDate,outcomeDate)){
                    addMoneyIncomeDisplay(income[incomeNum])
                    incomeNum++

                }
                else{
                    addMoneyOutcomeDisplay(outcome[outcomeNum])
                    outcomeNum++
                }
            }
        }while (true)
    }

    private fun addMoneyIncomeDisplay(income: moneyIncome) {
        val iconId = IconsStorage.getIncomeIconByType(income.type)
        createDisplay(iconId, income.value, income.date, income.type.name)
    }

    private fun addMoneyOutcomeDisplay(outcome: moneyOutcome) {
        val iconId = IconsStorage.getOutcomeIconByType(outcome.type)
        createDisplay(iconId, outcome.value, outcome.date, outcome.type.name)
    }

    private fun createDisplay(iconId : Int, price: Float, date: MyDate, purpose: String ){
        // Initialize a new CardView instance
        val cardView = CardView(this)

        // Initialize a new LayoutParams instance, CardView width and height
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, // CardView width
            LinearLayout.LayoutParams.WRAP_CONTENT // CardView height
        )

        // Set margins for card view
        layoutParams.setMargins(10, 10, 10, 10)

        // Set the card view layout params
        cardView.layoutParams = layoutParams

        // Set the card view corner radius
        cardView.radius = 16F

        // Set the card view content padding
        cardView.setContentPadding(25,25,25,25)

        // Set the card view background color
        cardView.setCardBackgroundColor(Color.LTGRAY)

        // Set card view elevation
        cardView.cardElevation = 8F

        // Set card view maximum elevation
        cardView.maxCardElevation = 12F

        // Set a click listener for card view
        cardView.setOnClickListener{
            Toast.makeText(
                applicationContext,
                "Card clicked.",
                Toast.LENGTH_SHORT).show()
        }

        // Add an ImageView to the CardView



        var imageView = generateImageView(iconId)


        // Finally, add the CardView in root layout
        var linearLayout = generateLinearLayout()
        linearLayout.addView(imageView)
        linearLayout.addView(generateTextView(date.getDateString()))

        var colorOfPrice = getColor(R.color.black)
        if(price < 0){
            colorOfPrice = Color.RED
        }
        else if(price == 0f){
            colorOfPrice = Color.GRAY
        }
        else
        {
            colorOfPrice = Color.GREEN
        }

        linearLayout.addView(generateTextView(price.toString(), colorOfPrice))

        cardView.addView(linearLayout)
        val root_layout = findViewById<LinearLayout>(R.id.root_layout)
        root_layout.addView(cardView)
    }

    private fun delete(purpose: String ) {

    }

    private fun generateImageView(imgId: Int): ImageView {
        val imageView = ImageView(this)
        val params = ConstraintLayout.LayoutParams(100,
            100)
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID

        imageView.layoutParams = params
        imageView.setImageResource(imgId)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return imageView
    }

    private fun generateTextView(text: String, color: Int = getColor(R.color.black)): TextView{
        val TextView = TextView(this)
        TextView.textSize = 14f
        val params = ConstraintLayout.LayoutParams(150,
            ViewGroup.LayoutParams.MATCH_PARENT)
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID

        TextView.layoutParams = params
        TextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        TextView.setTextColor(color)
        TextView.setText(text)

        return TextView
    }

    private fun generateLinearLayout(): LinearLayout{
        val layout = LinearLayout(this)
        var constraintLayout = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        constraintLayout.orientation = LinearLayout.HORIZONTAL
        layout.layoutParams = constraintLayout
        layout.gravity = Gravity.LEFT

        return layout
    }
}