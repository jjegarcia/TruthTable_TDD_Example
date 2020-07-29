package com.example.truthtable_tdd_example.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthtable_tdd_example.data.OilDataClass
import com.example.truthtable_tdd_example.R

class DetailsActivity : AppCompatActivity() {
    private var _currentOil= MutableLiveData<OilDataClass>()
    val currentOil: LiveData<OilDataClass> get()=_currentOil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

    }


    }

