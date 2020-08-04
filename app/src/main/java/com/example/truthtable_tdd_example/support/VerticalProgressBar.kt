package com.example.truthtable_tdd_example.support

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ProgressBar

class VerticalProgressBar(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ProgressBar(context, attrs, defStyleAttr, defStyleRes), Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("context"),
        TODO("attrs"),
        TODO("defStyleAttr"),
        TODO("defStyleRes")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VerticalProgressBar> {
        override fun createFromParcel(parcel: Parcel): VerticalProgressBar {
            return VerticalProgressBar(parcel)
        }

        override fun newArray(size: Int): Array<VerticalProgressBar?> {
            return arrayOfNulls(size)
        }
    }

}