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
}

