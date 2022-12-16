package com.oguzhanturkmen.mypharmacyonduty.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyResponse
import com.oguzhanturkmen.mypharmacyonduty.service.APIUtils
import retrofit2.Call
import retrofit2.Response

class PharmacyViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    val pharmacyList = MutableLiveData<List<PharmacyModel>>()


    fun getDatas(secilenilce: String, secilenil: String) {

        APIUtils.pharmacyAPIGet().getPharmacy(secilenilce, secilenil).enqueue(
            object : retrofit2.Callback<PharmacyResponse> {
                override fun onResponse(
                    call: Call<PharmacyResponse>,
                    response: Response<PharmacyResponse>
                ) {
                    Log.e("onResponse", response.body().toString())
                    val tempList = response.body()?.data
                    tempList?.let {
                        pharmacyList.value = it

                    }


                }

                override fun onFailure(call: Call<PharmacyResponse>, t: Throwable) {

                }


            })

    }

}




