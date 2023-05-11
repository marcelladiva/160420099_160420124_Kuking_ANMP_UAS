package com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Article
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Doctor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoctorListViewModel(application: Application): AndroidViewModel(application) {

    val doctorLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        doctorLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marcelladiva/160420124_MarcellaDivaViorent_UTS/main/doctor.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Doctor>>() { }.type
                val result = Gson().fromJson<ArrayList<Doctor>>(it, sType)
                doctorLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("volleyerror", it.toString())
                doctorLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}