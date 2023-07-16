package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//import com.google.gson.annotations.SerializedName

@Entity
data class Doctor(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val hari:String?,
    val jam:String?){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}

data class Drug(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val dosis:String?,
    val harga:String?,
    )

@Entity
data class Article(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}

data class Event(
    val id:String?,
    val name:String?,
    val photoUrl:String?,
    val detail:String?,
    val hari:String?,
    val pukul:String?,
    val biaya:String?,
)



