package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testCalculatorTip() {

        val baseTc = TipCalculation(checkAmount = 10.00)

        val checkAmount = 10.00
        val tipPctInput = 25

        val expectedTipResult = TipCalculation(
            checkAmount = checkAmount,
            tipPct = 25,
            tipAmount = 2.50,
            grandTotal = 12.50
        )

        assertEquals(expectedTipResult, calculator.calculatorTip(checkAmount, tipPctInput));
    }

    @Test
    fun testCalculatorTip2() {

        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVals = listOf(
            baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
            baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80)
        )

        testVals.forEach {
            assertEquals(it, calculator.calculatorTip(it.checkAmount, it.tipPct))
        }
    }

}