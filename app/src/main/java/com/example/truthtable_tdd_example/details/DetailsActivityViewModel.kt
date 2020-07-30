package com.example.truthtable_tdd_example.details

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truthtable_tdd_example.data.ItemData
import com.example.truthtable_tdd_example.data.OilDataClass
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.info_item.view.*
import javax.inject.Inject

class DetailsActivityViewModel @Inject constructor() : ViewModel() {
    //    var vinString: MutableLiveData<String> = MutableLiveData<String>()
//    var vinString: ObservableField<String> = ObservableField<String>("testg")
    lateinit var vinString: String
    lateinit var percentageString: String
    lateinit var stateString: String
    lateinit var dateString: String

    fun setVehicleData(view: View, oilDataClass: OilDataClass) {
        setItemData(view.vinItem, ItemData(label = "VIN:", value = oilDataClass.vin.toString()))
        setItemData(view.percentageItem, ItemData(label = "PERCENTAGE:", value = oilDataClass.percentage.toString()))
        setItemData(view.stateItem, ItemData(label = "STATE:", value = oilDataClass.state.toString()))
        setItemData(view.dateItem, ItemData(label = "DATE:", value = oilDataClass.date.toString()))
    }

    fun setPrognosticsData(oilDataClass: OilDataClass) {
        vinString = oilDataClass.vin.toString()
        percentageString = oilDataClass.percentage.toString()
        stateString = oilDataClass.state.toString()
        dateString = oilDataClass.date.toString()
    }

    fun setItemData(view: View, itemData: ItemData) {
        view.itemLabel.text = itemData.label
        view.itemValue.text = itemData.value
    }
}