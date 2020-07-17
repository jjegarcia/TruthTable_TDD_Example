package com.example.truthtable_tdd_example

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivityViewModel : ViewModel() {

    val mainSupport = MainSupport()

    fun buttonClickHandler(view: View) {
        Log.d("test", "buttom clicked")
//        mainSupport.launchIntent(view = view)
//        mainSupport.showSnackBar(view = view, message = "testing", length = Snackbar.LENGTH_LONG)
    }

}