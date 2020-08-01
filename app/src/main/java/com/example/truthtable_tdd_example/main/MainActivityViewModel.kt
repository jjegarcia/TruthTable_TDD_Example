package com.example.truthtable_tdd_example.main

import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truthtable_tdd_example.data.*
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.*
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider
) : ViewModel() {
    lateinit var oilLifePrognostics: OilLifePrognostics
    lateinit var vehicleStatus: VehicleStatus
    private val _launchSnackBarData = MutableLiveData<SnackBarDataClass>()
    val launchSnackBarData: LiveData<SnackBarDataClass> get() = _launchSnackBarData
    private val _launchDetailsAcitivityData = MutableLiveData<LauchActivityDataClass>()
    val launchDetailsAcitivityData: LiveData<LauchActivityDataClass> get() = _launchDetailsAcitivityData

    fun buttonClickHandler(view: View) {
        Log.d("test", "buttom clicked")
        launchDetailsActivity(view = view)
    }

    fun launchSnackBar(view: View, message: String, length: Int) {
        val snackData =
            SnackBarDataClass(
                view = view,
                message = message,
                length = length
            )
        _launchSnackBarData.postValue(snackData)
    }

    fun launchDetailsActivity(view: View) {
        val launchActivityData = zipAPIs(view)
    }

    fun getIntent(
        context: Context,
        arguments: OilMessageIntentArguments.LoadRequestArguments
    ): Intent {
        return oilLifeHealthDetailsIntentProvider.newIntent(
            context = context,
            arguments = arguments
        )
    }

    fun fetchOilLifePrognostics(oilLifePrognostics: OilLifePrognostics): Single<OilLifePrognostics> {
        return Single.just(oilLifePrognostics)
    }

    fun fetchVehicleStatus(vehicleStatus: VehicleStatus): Single<VehicleStatus> {
        return Single.just(vehicleStatus)
    }

    fun zipAPIs(view: View): Disposable? {
        return Single.zip(
            fetchOilLifePrognostics(oilLifePrognostics), fetchVehicleStatus(vehicleStatus),
            zipFunction
        )
            .subscribe(handleSubscription(view), this::handleError)
    }

    private fun handleSubscription(view: View): (Pair<OilLifePrognostics, VehicleStatus>) -> Unit {
        return {
            if (checkAPIErrorFree(
                    oilLifePrognostics = oilLifePrognostics,
                    vehicleStatus = vehicleStatus
                )
            ) {
                val arguments = OilMessageIntentArguments.LoadRequestArguments(
                    oilLifePrognostics = oilLifePrognostics,
                    oil = vehicleStatus
                )
                val intent = getIntent(context = view.context, arguments = arguments)
                _launchDetailsAcitivityData.postValue(
                    LauchActivityDataClass(
                        view,
                        intent
                    )
                )
            } else displayErrorSnackbar(view)
        }
    }

    val zipFunction =
        BiFunction<OilLifePrognostics, VehicleStatus, Pair<OilLifePrognostics, VehicleStatus>> { oilLifePrognostics, vehicleStatus ->
            oilLifePrognostics to vehicleStatus
        }

    private fun checkAPIErrorFree(
        oilLifePrognostics: OilLifePrognostics,
        vehicleStatus: VehicleStatus
    ): Boolean {
        val predicateA=!oilLifePrognostics.isError
        val predicateB1=vehicleStatus.vehicleStatusAuthorised
        val predicateB2=vehicleStatus.isLocationAuthorised
        val predicateB= predicateB1  && predicateB2
        val predicate= predicateA && predicateB
        return predicate
    }

    private fun displayErrorSnackbar(view: View) {
        launchSnackBar(view = view, message = "testing", length = Snackbar.LENGTH_LONG)
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun setOilLifePrognostic() {
        oilLifePrognostics =
            OilLifePrognostics(
                isError = false,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
    }

    private fun setVehicleStatus() {
        vehicleStatus =
            VehicleStatus(
                vehicleStatusAuthorised = true,
                isLocationAuthorised = true,
                oil = OilDataClass(
                    vin = "OIL_vin",
                    percentage = 25,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
    }
}