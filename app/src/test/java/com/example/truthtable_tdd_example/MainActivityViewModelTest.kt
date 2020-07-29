package com.example.truthtable_tdd_example

import android.content.Context
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.truthtable_tdd_example.main.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import io.mockk.*
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.rules.TestRule

class MainActivityViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `launchSnackBar - any condition - loads snackbar parameters`() {
        val subject =
            MainActivityViewModel()
        val view: View = mockk()
        val message = "testing"
        val length = Snackbar.LENGTH_LONG

        subject.launchSnackBar(view = view, message = message, length = length)

        assertThat(subject.launchSnackBarData.value!!.view).isEqualTo(view)
        assertThat(subject.launchSnackBarData.value!!.message).isEqualTo(message)
        assertThat(subject.launchSnackBarData.value!!.length).isEqualTo(length)
    }

    @Test
    fun `getIntent - any condition - returns Intent`() {
        val subject =
            MainActivityViewModel()
        val view: View = mockk()
        val context: Context = mockk()
        every { view.context } returns context


        val response = subject.getIntent(context = context)

        assertThat(response).isNotNull()
    }

    @Test
    fun `launchDetailsActivity - any condition - loads details activity parameter`() {
        val subject =
            MainActivityViewModel()
        val view: View = mockk()
        val context: Context = mockk()
        every { view.context } returns context

        subject.launchDetailsActivity(view = view)

        assertThat(subject.launchDetailsAcitivityData.value!!.view).isEqualTo(view)
    }

    @Test
    fun `zipAPIs - any condition - return zipped items`() {
        val subject =
            MainActivityViewModel()

        val response=subject.zipAPIs()
//        assertThat(response).isEqualTo()

    }

}

