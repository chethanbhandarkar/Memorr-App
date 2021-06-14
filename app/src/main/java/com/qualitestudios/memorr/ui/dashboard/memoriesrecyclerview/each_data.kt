package com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class each_data (val date:String,val title:String,val message:String)

sealed class each_data_sealed{
@Parcelize
    data class each_data_sealed_value (val date:String,val title:String,val message:String):each_data_sealed(),Parcelable
object Loading:each_data_sealed()



}