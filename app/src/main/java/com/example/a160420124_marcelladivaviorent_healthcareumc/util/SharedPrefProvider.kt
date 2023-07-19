package com.example.a160420124_marcelladivaviorent_healthcareumc.util

import android.content.Context
import android.content.SharedPreferences
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.User

private const val PREF_NAME = "healthcareumc"
private const val PREF_IS_LOGIN = "PREF_IS_LOGIN"
private const val PREF_USERNAME = "PREF_USERNAME"
private const val PREF_PASSWORD = "PREF_PASSWORD"
private const val PREF_USER = "PREF_USER"

class SharedPrefProvider(private val appContext: Context) {

    private val sharedPreferences: SharedPreferences
        get() = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getUsername(): String = sharedPreferences.getString(PREF_USERNAME, "").toString()
    fun getPassword(): String = sharedPreferences.getString(PREF_PASSWORD, "").toString()

    fun getUUID(): Int = sharedPreferences.getInt(PREF_USER, 0)

    fun getUser(): User = User(getUsername(), getPassword()).apply { uuid = getUUID() }

    fun isLogin(): Boolean = sharedPreferences.getBoolean(PREF_IS_LOGIN, false)

    fun sessionLogin(username: String, uuid: Int){
        sharedPreferences.edit().apply {
            putString(PREF_USERNAME, username)
            putInt(PREF_USER, uuid)
            putBoolean(PREF_IS_LOGIN, true)
        }.apply()
    }

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}