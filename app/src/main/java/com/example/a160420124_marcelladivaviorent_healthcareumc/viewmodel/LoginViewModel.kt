package com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.User
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.SharedPrefProvider
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    var userLD = MutableLiveData<User?>()
    val sharedPrefProvider = SharedPrefProvider(application.applicationContext)

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getUserFromSharedPref() {
        val user = sharedPrefProvider.getUser()
        userLD.postValue(user)
    }

    fun isLogin() = sharedPrefProvider.isLogin()

    fun sessionLogin(username: String, uuid: Int) = sharedPrefProvider.sessionLogin(username, uuid)

    fun logout() = sharedPrefProvider.logout()

    fun login(username: String, password: String) {
        var login = true

        if (username.isBlank() || password.isBlank())
            login = false

        if (login) {
            launch {
                val db = buildDb(getApplication())
                userLD.postValue(db.userDao().selectUserLogin(username, password))
            }
        }
    }

    fun signup(username: String, password: String) {
        var signup = true

        if (username.isBlank() || password.isBlank())
            signup = false

        if (signup) {
            val user = User(username, password)
            launch {
                buildDb(getApplication()).apply {
                    userDao().insertAll(user)
                    userLD.postValue(user)
                }
            }
        }
    }

    fun ubah(username: String, password: String) {
        getUserFromSharedPref()
        val oldUser = userLD.value

        launch {
            oldUser?.let {
                buildDb(getApplication()).apply {
                    userDao().update(username, password, it.uuid)
                    it.username = username
                    it.password = password
                    sharedPrefProvider.sessionLogin(username, it.uuid)
                    userLD.postValue(it)
                }
            }
        }
    }
    fun profile(uuid: Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(uuid))
        }
    }
}