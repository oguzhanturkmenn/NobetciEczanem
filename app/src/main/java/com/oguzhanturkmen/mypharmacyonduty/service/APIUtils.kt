package com.oguzhanturkmen.mypharmacyonduty.service

import com.oguzhanturkmen.mypharmacyonduty.util.Constants
import retrofit2.Retrofit

class APIUtils {
    companion object{

        fun pharmacyAPIGet() : PharmacyAPI{
            return RetrofitBuilder.getClient(Constants.BASE_URL)
                .create(PharmacyAPI::class.java)
        }
    }

}