package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel

import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.Calculator
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    fun calculateTip(){
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount !=null && tipPct != null){
            tipCalculation = calculator.calculatorTip(checkAmount, tipPct)
        }

    }
}