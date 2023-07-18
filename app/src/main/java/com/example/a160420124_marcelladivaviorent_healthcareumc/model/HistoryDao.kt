package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg history: History)

    @Query("SELECT * FROM history")
    fun selectAllHistory(): List<History>

    @Query("SELECT * FROM history WHERE userid= :id")
    fun selectHistory(id:String): List<History>

    @Delete
    fun deleteHistory(history: History)
}