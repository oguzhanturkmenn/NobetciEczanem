package com.oguzhanturkmen.mypharmacyonduty.service

import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyResponse
import com.oguzhanturkmen.mypharmacyonduty.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PharmacyAPI {

    @Headers(
        "authorization: ${API_KEY}"
    )
    @GET("pharmacy")
     fun getPharmacy(
        @Query("county") secilenilce:String,
        @Query("city") secilenil:String
    ): Call<PharmacyResponse>

}