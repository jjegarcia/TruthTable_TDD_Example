package com.example.truthtable_tdd_example

import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.mockk.every
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import org.junit.Test

class MainSupportTest {
    @Test
    fun `showSnackBar- with  message and view - displays snackBar()`() {
        val subject: MainSupport = MainSupport()
        val message:String="test"
        val view:View= mockk()
        //val snackBarSlot= slot<Snackbar>()
        every { Snackbar.make(view,message) } just runs


    }
}