package com.oguzhanturkmen.mypharmacyonduty.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityModel(
    val il:String,
    val ilceleri:ArrayList<String>,
    val plaka:Int
    )
{

}
