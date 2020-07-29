package com.example.truthtable_tdd_example.di

import android.app.Application
import android.os.UserManager

open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
//        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.builder().build()
    }

//    open val userManager by lazy {
//        UserManager(SharedPreferencesStorage(this))
//    }
}
