package com.example.truthtable_tdd_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.truthtable_tdd_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(layoutInflater)
        inflate.viewModel= MainActivityViewModel()
        setContentView(inflate.root)
    }

}
