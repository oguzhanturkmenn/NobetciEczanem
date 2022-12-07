package com.oguzhanturkmen.mypharmacyonduty.models

import java.io.Serializable

data class PharmacyModel(
    val EczaneAdi : String,
    val ilce : String,
    val Adresi : String,
    val Telefon : String,
    val longitude : String,
    val latitude : String
) : Serializable
