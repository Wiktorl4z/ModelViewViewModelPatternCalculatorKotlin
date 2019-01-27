package pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.R
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.databinding.ActivityTipCalculatorBinding

import kotlinx.android.synthetic.main.activity_tip_calculator.*
import pl.futuredev.modelviewviewmodelpatterncalculatorkotlin.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        setSupportActionBar(toolbar)

    }

}
