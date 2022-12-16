package com.oguzhanturkmen.mypharmacyonduty.models


data class PharmacyResponse(
    val `data`: List<PharmacyModel>,
    val message: String,
    val rowCount: Int,
    val status: String
)

