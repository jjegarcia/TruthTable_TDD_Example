package com.example.truthtable_tdd_example

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
   private val _launchSnackBarData = MutableLiveData<SnackBarDataClass>()
    val launchSnackBarData:LiveData<SnackBarDataClass> get() = _launchSnackBarData
    private val _launchDetailsAcitivityData = MutableLiveData<LauchActivityDataClass>()
    val launchDetailsAcitivityData:LiveData<LauchActivityDataClass> get() = _launchDetailsAcitivityData

    fun buttonClickHandler(view: View) {
        Log.d("test", "buttom clicked")
//        launchSnackBar(view = view, message = "testing", length = Snackbar.LENGTH_LONG)
        launchDetailsActivity(view = view)
    }

    fun launchSnackBar(view: View, message: String, length: Int) {
        val snackData = SnackBarDataClass(view = view, message = message, length = length)
        _launchSnackBarData.postValue(snackData)
    }

    fun launchDetailsActivity(view: View){
        val launchActivityData=LauchActivityDataClass(view = view ,intent = getIntent(view.context))
        _launchDetailsAcitivityData.postValue(launchActivityData)
    }
    fun getIntent(context: Context):Intent{
        return Intent(context,DetailsActivity::class.java)
    }
}