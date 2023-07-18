package com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Facility
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.History
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val historyLD = MutableLiveData<List<History>>()
    val historyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(userID:Int) {
        loadingLD.value = true
        historyLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            historyLD.postValue(db.historyDao().selectHistory(userID.toString()))
        }
    }
}