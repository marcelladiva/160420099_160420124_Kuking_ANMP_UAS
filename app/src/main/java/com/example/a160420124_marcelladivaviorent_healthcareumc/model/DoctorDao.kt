package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg doctor: Doctor)

    @Query("SELECT * FROM doctor")
    fun selectAllDoctor(): List<Doctor>

    @Query("SELECT * FROM doctor WHERE uuid= :id")
    fun selectDoctor(id:Int): Doctor

    @Delete
    fun deleteDoctor(doctor: Doctor)
}