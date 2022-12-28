package com.oguzhanturkmen.mypharmacyonduty.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {
    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getClient(base_url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}

