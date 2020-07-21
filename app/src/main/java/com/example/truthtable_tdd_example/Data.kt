package com.example.truthtable_tdd_example

import android.content.Intent
import android.view.View

data class SnackBarDataClass(
    val view: View,
    val message: String,
    val length: Int
)

data class LauchActivityDataClass(
    val view: View,
    val intent: Intent
)

data class VehicleStatus(
    val vehicleStatusAuthorised: Boolean,
    val isLocationAuthorised: Boolean
)

data class OilLifePrognostics(
    val isError: Boolean
)
