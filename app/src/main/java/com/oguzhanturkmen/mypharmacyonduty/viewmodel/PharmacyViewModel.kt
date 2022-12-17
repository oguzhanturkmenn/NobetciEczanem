package com.oguzhanturkmen.mypharmacyonduty.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyResponse
import com.oguzhanturkmen.mypharmacyonduty.service.APIUtils
import com.oguzhanturkmen.mypharmacyonduty.util.showSnackbar
import com.oguzhanturkmen.mypharmacyonduty.util.showToast
import com.yagmurerdogan.toasticlib.Toastic
import retrofit2.Call
import retrofit2.Response

class PharmacyViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    val pharmacyList = MutableLiveData<List<PharmacyModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDatas(secilenilce: String, secilenil: String) {
        isLoading.value = true
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
                        isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<PharmacyResponse>, t: Throwable) {
                    if (!hasInternetConnection()){
                            Toastic.toastic(
                                context = getApplication(),
                                message = "İnternetiniz açık olmalıdır!",
                                duration = Toastic.LENGTH_SHORT,
                                type = Toastic.ERROR,
                                isIconAnimated = true
                            ).show()

                    }
                    isLoading.value = false
                }


            })
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}




