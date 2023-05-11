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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleDetailViewModel(application: Application): AndroidViewModel(application) {
    val articleLD = MutableLiveData<Article>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idArticle : String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marcelladiva/160420124_MarcellaDivaViorent_UTS/main/article.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Article>>() { }.type
                val result = Gson().fromJson<ArrayList<Article>>(it, sType)

                for(a in result){
                    if(a.id == idArticle){
                        articleLD.value = a
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