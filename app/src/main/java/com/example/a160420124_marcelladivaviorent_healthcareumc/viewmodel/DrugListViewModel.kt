package com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Article
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Doctor
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Drug
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DrugListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val drugLD = MutableLiveData<List<Drug>>()
    val drugLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        drugLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            drugLD.postValue(db.drugDao().selectAllDrug())
        }
    }
}