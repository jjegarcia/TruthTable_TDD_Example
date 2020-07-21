package com.example.truthtable_tdd_example

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivityViewModel : ViewModel() {
   private val _launchSnackBarData = MutableLiveData<SnackBarDataClass>()
    val launchSnackBarData:LiveData<SnackBarDataClass> get() = _launchSnackBarData

    fun buttonClickHandler(view: View) {
        Log.d("test", "buttom clicked")
//        mainSupport.launchIntent(view = view)
//        mainSupport.showSnackBar(view = view, message = "testing", length = Snackbar.LENGTH_LONG)
//        launchSnackBar(view = view, message = "testing", length = Snackbar.LENGTH_LONG)
    }

    fun launchSnackBar(view: View, message: String, length: Int) {
        val snackData = SnackBarDataClass(view, message, length)
        _launchSnackBarData.postValue(snackData)
    }

}