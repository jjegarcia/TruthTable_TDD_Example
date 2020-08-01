package com.example.truthtable_tdd_example.main

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
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import java.util.*
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider
) : ViewModel() {
    lateinit var oilLifePrognostics: OilLifePrognostics
    lateinit var vehicleStatus: VehicleStatus
    lateinit var ccsResponse: CcsResponse
    private val _launchSnackBarData = MutableLiveData<SnackBarDataClass>()
    val launchSnackBarData: LiveData<SnackBarDataClass> get() = _launchSnackBarData
    private val _launchDetailsAcitivityData = MutableLiveData<LauchActivityDataClass>()
    val launchDetailsAcitivityData: LiveData<LauchActivityDataClass> get() = _launchDetailsAcitivityData

    fun buttonClickHandler(view: View) {
        Log.d("test", "buttom clicked")
//        setOilLifePrognostic()
//        setVehicleStatus()
//        setCcsResponse()
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

    fun fetchCcsResponse(ccsResponse: CcsResponse): Single<CcsResponse> {
        return Single.just(ccsResponse)
    }

    fun zipAPIs(view: View): Disposable? {
        return Single.zip(
            fetchOilLifePrognostics(oilLifePrognostics),
            fetchVehicleStatus(vehicleStatus),
            fetchCcsResponse(ccsResponse),
            zipFunction
        )
            .subscribe(handleSubscription(view), { this.handleError(throwable = it) })
    }

    private fun handleSubscription(view: View): (Pair<Pair<OilLifePrognostics,VehicleStatus>, CcsResponse>) -> Unit {
         return {
            if (APIsFail(
                    oilLifePrognostics = oilLifePrognostics,
                    vehicleStatus = vehicleStatus,
                    ccsResponse= ccsResponse
                ) )displayErrorSnackbar(view)
            else {
                val arguments = OilMessageIntentArguments.LoadRequestArguments(
                    oilLifePrognostics = oilLifePrognostics,
                    oil = vehicleStatus,
                    ccsResponse = ccsResponse
                )
                val intent = getIntent(context = view.context, arguments = arguments)
                _launchDetailsAcitivityData.postValue(
                    LauchActivityDataClass(
                        view,
                        intent
                    )
                )
            }
        }
    }

    val zipFunction = Function3 { oilLifePrognostics: OilLifePrognostics, vehicleStatus: VehicleStatus, ccsResponse: CcsResponse->
            oilLifePrognostics to vehicleStatus to ccsResponse
        }

    private fun APIsFail(
        oilLifePrognostics: OilLifePrognostics,
        vehicleStatus: VehicleStatus,
        ccsResponse: CcsResponse
    ): Boolean {
        return false
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
    private fun setCcsResponse(){
        ccsResponse=
            CcsResponse(isError = false)
    }
}