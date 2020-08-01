package com.example.truthtable_tdd_example

import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.truthtable_tdd_example.data.*
import com.example.truthtable_tdd_example.main.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import io.mockk.*
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.rules.TestRule
import java.util.*
import com.example.truthtable_tdd_example.data.OilLifeHealthDetailsIntentProvider as OilLifeHealthDetailsIntentProvider

class MainActivityViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `getIntent - any condition - returns Intent`() {
        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        val arguments: OilMessageIntentArguments.LoadRequestArguments = mockk()
        val intent: Intent = mockk()
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent

        val response = subject.getIntent(context = context, arguments = arguments)

        assertThat(response).isNotNull()
        assertThat(response).isEqualTo(intent)
    }

    @Test
    fun `zipAPIs - APIsFail forced to false - return zipped items`() {
        val learnMore_button_pushed: Boolean = true
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = true,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
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
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed=learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value!!.intent).isEqualTo(intent)
    }

    @Test
    fun `launchSnackBar - any condition - loads snackbar parameters`() {
        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val message = "testing"
        val length = Snackbar.LENGTH_LONG

        subject.launchSnackBar(view = view, message = message, length = length)

        assertThat(subject.launchSnackBarData.value!!.view).isEqualTo(view)
        assertThat(subject.launchSnackBarData.value!!.message).isEqualTo(message)
        assertThat(subject.launchSnackBarData.value!!.length).isEqualTo(length)
    }

    @Test
    fun `zipAPIs - Button pushed and APIsFail forced to false  - return zipped items`() {
        val learnMore_button_pushed: Boolean = true
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = true,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
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
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value!!.intent).isEqualTo(intent)
    }

    @Test
    fun `zipAPIs - Button not pushed and APIsFail forced to false  - doesn't zipped items`() {
        val learnMore_button_pushed: Boolean = false
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = true,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
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
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value).isEqualTo(null)
    }

    @Test
    fun `zipAPIs - Button pushed and OLP VS not fail  - return zipped items`() {
        val learnMore_button_pushed: Boolean = true
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = false,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
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
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value!!.intent).isEqualTo(intent)
    }

    @Test
    fun `zipAPIs - Button not pushed and OLP VS not fail   - doesn't zipped items`() {
        val learnMore_button_pushed: Boolean = false
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = false,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
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
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value).isEqualTo(null)
    }

    @Test
    fun `zipAPIs - Button pushed and OLP VS fail  - doesn't zipped items`() {
        val learnMore_button_pushed: Boolean = true
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = true,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
            VehicleStatus(
                vehicleStatusAuthorised = false,
                isLocationAuthorised = false,
                oil = OilDataClass(
                    vin = "OIL_vin",
                    percentage = 25,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value).isEqualTo(null)
    }

    @Test
    fun `zipAPIs - Button not pushed  and OLP VS fail  - doesn't zipped items`() {
        val learnMore_button_pushed: Boolean = false
        var oilLifePrognostics: OilLifePrognostics =
            OilLifePrognostics(
                isError = true,
                oil = OilDataClass(
                    vin = "OLP_vin",
                    percentage = 50,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var vehicleStatus: VehicleStatus =
            VehicleStatus(
                vehicleStatusAuthorised = false,
                isLocationAuthorised = false,
                oil = OilDataClass(
                    vin = "OIL_vin",
                    percentage = 25,
                    state = OilState.GOOD,
                    date = Date()
                )
            )
        var ccsResponse: CcsResponse =
            CcsResponse(isError = true)

        val oilLifeHealthDetailsIntentProvider: OilLifeHealthDetailsIntentProvider = mockk()
        val subject = MainActivityViewModel(oilLifeHealthDetailsIntentProvider)
        val view: View = mockk()
        val intent: Intent = mockk()
        val context: Context = mockk()
        every { view.context } returns context
        var arguments = OilMessageIntentArguments.LoadRequestArguments(
            oilLifePrognostics = oilLifePrognostics,
            oil = vehicleStatus,
            ccsResponse = ccsResponse
        )
        every { oilLifeHealthDetailsIntentProvider.newIntent(context, arguments) } returns intent
        subject.vehicleStatus = vehicleStatus
        subject.oilLifePrognostics = oilLifePrognostics
        subject.ccsResponse = ccsResponse
        subject.learnMoreButtonPushed = learnMore_button_pushed
        val response = subject.zipAPIs(view = view)

        assertThat(subject.launchDetailsAcitivityData.value).isEqualTo(null)
    }

}

