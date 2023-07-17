package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface FacilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg facility: Facility)

    @Query("SELECT * FROM facility")
    fun selectAllFacility(): List<Facility>

    @Query("SELECT * FROM facility WHERE uuid= :id")
    fun selectFacility(id:Int): Facility

    @Delete
    fun deleteFacility(facility: Facility)
}