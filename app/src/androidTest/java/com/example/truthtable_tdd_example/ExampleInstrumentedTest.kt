package com.example.truthtable_tdd_example

import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.google.android.material.snackbar.Snackbar
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.truthtable_tdd_example", appContext.packageName)
    }
}
