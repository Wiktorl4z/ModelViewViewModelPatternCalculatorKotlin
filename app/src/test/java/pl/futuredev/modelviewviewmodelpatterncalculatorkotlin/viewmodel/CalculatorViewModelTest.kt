package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.Calculator
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.model.TipCalculation

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel


    @Mock
    lateinit var mockCalculator: Calculator

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        calculatorViewModel = CalculatorViewModel(mockCalculator)
    }

    @Test
    fun testCalculatorTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)

        `when`(mockCalculator.calculatorTip(10.00, 15)).thenReturn(stub)

        calculatorViewModel.calculateTip()

        assertEquals(stub, calculatorViewModel.tipCalculation)
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