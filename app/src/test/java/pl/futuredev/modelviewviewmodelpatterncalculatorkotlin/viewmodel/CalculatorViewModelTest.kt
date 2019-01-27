package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel

import android.app.Application
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.R
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.Calculator
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.TipCalculation

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel


    @Mock
    lateinit var mockCalculator: Calculator

    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResources(0.0,"$0.00")
        calculatorViewModel = CalculatorViewModel(application,mockCalculator)
    }

    private fun stubResources(given: Double, returnStub: String){
        `when` (application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculatorTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)

        `when`(mockCalculator.calculatorTip(10.00, 15)).thenReturn(stub)
        stubResources(10.0,"$10.00")
        stubResources(1.5,"$1.50")
        stubResources(11.5,"$11.50")

        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)
    }

    @Test
    fun testCalculateTipTipPercentage(){

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculatorTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())

    }

    @Test
    fun testCalculateTipBadCheckInputAmount(){

        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculatorTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())

    }
}