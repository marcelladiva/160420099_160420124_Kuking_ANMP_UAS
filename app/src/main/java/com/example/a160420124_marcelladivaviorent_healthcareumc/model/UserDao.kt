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

    @Query("UPDATE user SET username= :username, password= :password WHERE uuid= :id")
    suspend fun update(username: String, password: String, id: Int)

    @Delete
    fun deleteUser(user: User)
}