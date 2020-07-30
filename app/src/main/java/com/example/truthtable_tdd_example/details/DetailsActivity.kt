package com.example.truthtable_tdd_example.details

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthtable_tdd_example.data.OilDataClass
import com.example.truthtable_tdd_example.R
import com.example.truthtable_tdd_example.data.LAUNCH_HEALTH_DETAILS_ARGUMENTS
import com.example.truthtable_tdd_example.data.OilMessageIntentArguments
import com.example.truthtable_tdd_example.databinding.ActivityDetailsBinding
import com.example.truthtable_tdd_example.di.MyApplication
import com.example.truthtable_tdd_example.main.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    private var _currentOil = MutableLiveData<OilDataClass>()
    val currentOil: LiveData<OilDataClass> get() = _currentOil

    @Inject
    lateinit var detailsActivityViewModel: DetailsActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (application as MyApplication).appComponent.inject(this) //1
        val arguments = intent.getParcelableExtra<OilMessageIntentArguments.LoadRequestArguments>(
            LAUNCH_HEALTH_DETAILS_ARGUMENTS
        )
        ActivityDetailsBinding.inflate(
            LayoutInflater.from(this)
        ).apply {
            setContentView(root)
            viewModel = detailsActivityViewModel
        }
        detailsActivityViewModel.setVehicleData(view =ActivityDetails.rootView , oilDataClass = arguments.oil.oil)
    }

}

