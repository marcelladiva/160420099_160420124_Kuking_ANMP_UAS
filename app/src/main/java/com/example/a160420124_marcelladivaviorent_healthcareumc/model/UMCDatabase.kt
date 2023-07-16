package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.MIGRATION_1_2

@Database(entities = [Doctor::class, Article::class], version = 2)
abstract class UMCDatabase:RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile private var instance: UMCDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UMCDatabase::class.java,
                "umcdb").addMigrations(MIGRATION_1_2).build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}