package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.nfc.Tag
import android.util.Log
import kotlinx.android.synthetic.main.content_main.view.*
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.R
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.Calculator
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.TipCalculation

class CalculatorViewModel(val app: Application, val calculator: Calculator = Calculator()) : BaseObservable() {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tipCalculation: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputTotalDollarAmount = app.getString(R.string.dollar_amount, tipCalculation.grandTotal)
    }

    fun calculateTip() {

        // Log.d(TAG, "calculateTipInvoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            //    Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            updateOutputs(tipCalculation = calculator.calculatorTip(checkAmount, tipPct))
            notifyChange()
            clearInputs()
        }

    }

    private fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}