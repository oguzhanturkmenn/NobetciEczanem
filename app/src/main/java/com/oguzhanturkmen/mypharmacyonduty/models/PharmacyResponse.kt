package com.oguzhanturkmen.mypharmacyonduty.models

import com.google.gson.annotations.SerializedName

data class PharmacyResponse(
    @SerializedName("data")
    val data : List<PharmacyModel>,
    @SerializedName("success")
    val success : Boolean
)
