package com.example.truthtable_tdd_example.di

import com.example.truthtable_tdd_example.data.OilLifeHealthDetailsIntentProvider
import com.example.truthtable_tdd_example.details.DetailsActivity
import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
abstract class IntentModule {
    @Binds
    abstract  fun provideOilLifeIntent (activity : ActivityProvider): OilLifeHealthDetailsIntentProvider
}

class ActivityProvider @Inject constructor() : OilLifeHealthDetailsIntentProvider(){
    override fun getClazz(): Class<out Any> {
        return DetailsActivity::class.java
    }
}