package com.example.truthtable_tdd_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.example.truthtable_tdd_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mainSupport=MainSupport()
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(layoutInflater)
        mainActivityViewModel= MainActivityViewModel()
//        inflate.viewModel= mainActivityViewModel()
//        mainActivityViewModel.
//        setContentView(inflate.root)
        inflateBinding()
        mainActivityViewModel.launchSnackBarData.observe(
        this, Observer { mainSupport.showSnackBar(it.view,it.message,it.length) }
        )
    }

    private fun inflateBinding() {
        ActivityMainBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)
            lifecycleOwner = this@MainActivity
            viewModel = mainActivityViewModel
        }
    }
}
