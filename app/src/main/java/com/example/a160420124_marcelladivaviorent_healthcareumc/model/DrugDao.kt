package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg drug: Drug)

    @Query("SELECT * FROM drug")
    fun selectAllDrug(): List<Drug>

    @Query("SELECT * FROM drug WHERE uuid= :id")
    fun selectDrug(id:Int): Drug

    @Delete
    fun deleteDrug(drug: Drug)
}