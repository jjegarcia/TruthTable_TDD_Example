package com.example.truthtable_tdd_example

import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainSupport{
    fun showSnackBar(message:String,view: View){
        var snackBar: Snackbar = Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }
}