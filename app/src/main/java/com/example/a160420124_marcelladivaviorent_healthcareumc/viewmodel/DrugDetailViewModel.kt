package com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Doctor
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Drug
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DrugDetailViewModel(application: Application): AndroidViewModel(application){
    val drugLD = MutableLiveData<Drug>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idDrug : String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marcelladiva/160420124_MarcellaDivaViorent_UTS/main/drug.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Drug>>() { }.type
                val result = Gson().fromJson<ArrayList<Drug>>(it, sType)

                for(a in result){
                    if(a.id == idDrug){
                        drugLD.value = a
                    }
                }
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}