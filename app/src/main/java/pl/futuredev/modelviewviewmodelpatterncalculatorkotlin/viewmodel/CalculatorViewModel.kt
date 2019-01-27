package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.Calculator
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    fun calculateTip(){

        Log.d(TAG, "calculateTipInvoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount !=null && tipPct != null){
            tipCalculation = calculator.calculatorTip(checkAmount, tipPct)
        }

    }
}