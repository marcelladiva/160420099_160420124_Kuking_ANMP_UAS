package com.example.a160420124_marcelladivaviorent_healthcareumc.model

//import com.google.gson.annotations.SerializedName

data class Doctor(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val hari:String?,
    val jam:String?,
)

data class Drug(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val dosis:String?,
    val harga:String?,
    )

data class Article(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
)

data class Event(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val hari:String?,
    val pukul:String?,
    val biaya:String?,
)



