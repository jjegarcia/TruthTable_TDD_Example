package com.example.truthtable_tdd_example.details

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.truthtable_tdd_example.data.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.info_item.view.*
import kotlinx.android.synthetic.main.level_indicator.view.*
import javax.inject.Inject
import kotlin.math.roundToInt

class DetailsActivityViewModel @Inject constructor() : ViewModel() {
    //    private lateinit var vinString: String
//    private lateinit var percentageString: String
//    private lateinit var stateString: String
//    private lateinit var dateString: String
    private var factor: Float? = null

    fun setVehicleData(view: View, oilDataClass: OilDataClass) {
        setItemData(view.vinItem, ItemData(label = "VIN:", value = oilDataClass.vin))
        setItemData(
            view.percentageItem,
            ItemData(label = "PERCENTAGE:", value = oilDataClass.percentage.toString())
        )
        setItemData(
            view.stateItem,
            ItemData(label = "STATE:", value = oilDataClass.state.toString())
        )
        setItemData(view.dateItem, ItemData(label = "DATE:", value = oilDataClass.date.toString()))
    }

    //    fun setPrognosticsData(oilDataClass: OilDataClass) {
//        vinString = oilDataClass.vin.toString()
//        percentageString = oilDataClass.percentage.toString()
//        stateString = oilDataClass.state.toString()
//        dateString = oilDataClass.date.toString()
//    }
//
    fun setItemData(view: View, itemData: ItemData) {
        view.itemLabel.text = itemData.label
        view.itemValue.text = itemData.value
    }

    fun setLevelIndicatorData(view: View, percentage: Int) {
        factor = view.context.getResources().getDisplayMetrics().density
        when {
            ColorRange.GREEN.percentageRange.contains(percentage) -> {
                drawPartialGreenBox(view, percentage)
                drawFullYellowBox(view)
                drawFullRedBox(view)
            }
            ColorRange.YELLOW.percentageRange.contains(percentage) -> {
                view.greenLevelBox.visibility = View.GONE
                drawPartialYellowBox(view, percentage)
                drawFullRedBox(view)
            }
            ColorRange.RED.percentageRange.contains(percentage) -> {
                view.greenLevelBox.visibility = View.GONE
                view.yellowLevelBox.visibility = View.GONE
                drawPartialRedBox(view, percentage)
            }
            else -> {
                view.greenLevelBox.visibility = View.GONE
                view.yellowLevelBox.visibility = View.GONE
                view.redLevelBox.visibility = View.GONE
            }
        }
    }

    private fun drawFullYellowBox(view: View) {
        view.yellowLevelBox.apply {
            setBackgroundColor(LevelColor.YELLOW.color)
            layoutParams.height =
                convertToPixels(ColorRange.YELLOW.percentageRange.last-ColorRange.YELLOW.percentageRange.first)
        }
    }

    private fun drawFullRedBox(view: View) {
        view.redLevelBox.apply {
            setBackgroundColor(LevelColor.RED.color)
            view.redLevelBox.layoutParams.height =
                convertToPixels(ColorRange.RED.percentageRange.last-ColorRange.RED.percentageRange.first)
        }
    }

    private fun drawPartialGreenBox(view: View, percentage: Int) {
        view.greenLevelBox.apply {
            setBackgroundColor(LevelColor.GREEN.color)
            layoutParams.height =
                convertToPixels(percentage - ColorRange.GREEN.percentageRange.first
                )
        }
    }

    private fun drawPartialYellowBox(view: View, percentage: Int) {
        view.yellowLevelBox.apply {
            setBackgroundColor(LevelColor.YELLOW.color)
            layoutParams.height =
                convertToPixels(percentage - ColorRange.YELLOW.percentageRange.first)
        }
    }

    private fun drawPartialRedBox(view: View, percentage: Int) {
        view.redLevelBox.apply {
            setBackgroundColor(LevelColor.RED.color)
            layoutParams.height =
                convertToPixels(percentage - ColorRange.RED.percentageRange.first)
        }
    }

    fun convertToPixels(value: Int): Int = value * factor!!.roundToInt()
}