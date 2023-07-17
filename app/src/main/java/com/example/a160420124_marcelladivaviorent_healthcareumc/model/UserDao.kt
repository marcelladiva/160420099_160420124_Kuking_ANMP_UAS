package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    fun selectUserLogin(username:String, password:String): User

    @Query("SELECT * FROM user WHERE uuid= :id")
    fun selectUser(id:Int): User

    @Delete
    fun deleteUser(user: User)
}