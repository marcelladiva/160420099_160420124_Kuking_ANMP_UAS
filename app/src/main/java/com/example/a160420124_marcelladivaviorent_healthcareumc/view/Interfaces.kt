package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.view.View
import android.widget.CompoundButton
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Doctor

interface DoctorItemLayoutInterface {
    fun onButtonClick(v: View)
}

interface ArticleItemLayoutInterface {
    fun onButtonClick(v: View)
}

interface DrugItemLayoutInterface {
    fun onButtonClick(v: View)
}

interface HistoryItemLayoutInterface {
    fun onButtonClick(v: View)
}

interface LoginLayoutInterface {
    fun login(v: View)

    fun daftar(v: View)
}

interface SignUpLayoutInterface {
    fun daftar(v: View)
}

interface ProfileLayoutInterface {
    fun ubah(v: View)

    fun logout(v: View)

    fun history(v: View)
}