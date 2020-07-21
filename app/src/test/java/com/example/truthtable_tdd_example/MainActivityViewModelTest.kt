package com.example.truthtable_tdd_example

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import io.mockk.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class MainActivityViewModelTest {
    @Test
    fun `launchSnackBar - any condition - loads snackbar parameters`() {
        val subject = MainActivityViewModel()
        val view: View = mockk()
        val message = "testing"
        val length = Snackbar.LENGTH_LONG
        val context: Context = mockk()
        val _launchSnackBarData: MutableLiveData<SnackBarDataClass> = mockk()
        val snackData = CapturingSlot<SnackBarDataClass>()
        every { _launchSnackBarData.postValue(capture(snackData)) } just runs
        every { view.context } returns context


        subject.launchSnackBar(view = view, message = message, length = length)

        assertThat(snackData.captured.view).isEqualTo(view)
//        assertThat(snackData.captured.message).isEqualTo(message)
//        assertThat(snackData.captured.length ).isEqualTo(length)
    }
}

