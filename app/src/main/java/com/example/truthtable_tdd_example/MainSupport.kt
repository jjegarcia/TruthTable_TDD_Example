package com.example.truthtable_tdd_example

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainSupport{
    fun showSnackBar(message:String,view: View){
        var snackbar: Snackbar = Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_LONG
        )
        snackbar
        snackbar.show()
    }
}