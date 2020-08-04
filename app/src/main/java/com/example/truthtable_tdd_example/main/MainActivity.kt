package com.example.truthtable_tdd_example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.example.truthtable_tdd_example.data.OilLifeHealthDetailsIntentProvider
import com.example.truthtable_tdd_example.support.MainSupport
import com.example.truthtable_tdd_example.databinding.ActivityMainBinding
import com.example.truthtable_tdd_example.di.MyApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    val mainSupport = MainSupport()

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
//    var showBar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this) //1
        inflateBinding()
        mainActivityViewModel.launchSnackBarData.observe(
            this, Observer {
                mainSupport.showSnackBar(view = it.view, message = it.message, length = it.length)
            }
        )
        mainActivityViewModel.launchDetailsAcitivityData.observe(
            this, Observer {
                mainSupport.launchIntent(view = it.view, intent = it.intent)
            }
        )
//        mainActivityViewModel.showBar.observe(
//            this,
//            Observer { showBar }
//        )
    }

    private fun inflateBinding() {
        ActivityMainBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)
            lifecycleOwner = this@MainActivity
            viewModel = mainActivityViewModel
        }
    }
}
