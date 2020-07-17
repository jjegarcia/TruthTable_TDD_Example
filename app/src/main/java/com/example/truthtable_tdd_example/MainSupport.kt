package com.example.truthtable_tdd_example

import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainSupport{

    fun showSnackBar(view: View,message:String, length: Int){
        var snackBar: Snackbar = Snackbar.make(
            view,
            message,
            length
        )
        snackBar.show()
    }

    fun launchIntent(view: View){
        val intent=Intent(view.context,DetailsActivity::class.java)
        view.getContext().startActivity(intent)
    }

}