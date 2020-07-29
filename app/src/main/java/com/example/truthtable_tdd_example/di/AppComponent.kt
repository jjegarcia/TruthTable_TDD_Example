package com.example.truthtable_tdd_example.di


import com.example.truthtable_tdd_example.details.DetailsActivity
import com.example.truthtable_tdd_example.main.MainActivity
import dagger.Component

// Definition of a Dagger component
@Component(modules = [IntentModule::class])
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)

}