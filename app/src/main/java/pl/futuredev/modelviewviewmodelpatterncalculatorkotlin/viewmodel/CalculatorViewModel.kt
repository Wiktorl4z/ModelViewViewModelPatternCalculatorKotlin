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

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

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
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.grandTotal)
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