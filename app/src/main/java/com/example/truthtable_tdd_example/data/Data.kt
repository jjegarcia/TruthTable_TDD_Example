package com.example.truthtable_tdd_example.data

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import com.example.truthtable_tdd_example.details.DetailsActivity
import dagger.Binds
import dagger.Module
import kotlinx.android.parcel.Parcelize
import java.util.*
import javax.inject.Inject

data class ItemData(
    val label:String,
    val value:String
)
data class SnackBarDataClass(
    val view: View,
    val message: String,
    val length: Int
)

data class LauchActivityDataClass(
    val view: View,
    val intent: Intent
)

@Parcelize
data class OilDataClass(
    val vin: String,
    val percentage: Int,
    val state: OilState,
    val date: Date?
):Parcelable

enum class OilState {
    GOOD,
    LOW,
    ERROR
}

@Parcelize
data class VehicleStatus(
    var vehicleStatusAuthorised: Boolean,
    var isLocationAuthorised: Boolean,
    var oil: OilDataClass
):Parcelable

@Parcelize
data class OilLifePrognostics(
    var isError: Boolean,
    var oil: OilDataClass
):Parcelable

@Parcelize
data class CcsResponse(
    var isError: Boolean
):Parcelable

class OilMessageIntentArguments {
    @Parcelize
    data class LoadRequestArguments(
        val oilLifePrognostics: OilLifePrognostics,
        val oil: VehicleStatus,
        val ccsResponse: CcsResponse
    ):Parcelable
}

const val LAUNCH_HEALTH_DETAILS_ARGUMENTS = "launchHealthDetailsArguments"

abstract class IntentProvider {
    abstract fun getClazz(): Class<out Any>
    fun newIntent(context: Context): Intent {
        return Intent(context, getClazz())
    }
    fun newIntent(context: Context, arguments: OilMessageIntentArguments.LoadRequestArguments): Intent =
        newIntent(context).putExtra(LAUNCH_HEALTH_DETAILS_ARGUMENTS,arguments)
}
abstract class OilLifeHealthDetailsIntentProvider : IntentProvider()
