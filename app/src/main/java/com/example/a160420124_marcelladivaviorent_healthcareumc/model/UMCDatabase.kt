package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.*

@Database(entities = [Doctor::class, Article::class, Facility::class, Drug::class, User::class, History::class], version = 7)
abstract class UMCDatabase:RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun articleDao(): ArticleDao
    abstract fun facilityDao(): FacilityDao
    abstract fun drugDao(): DrugDao
    abstract fun userDao(): UserDao

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile private var instance: UMCDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UMCDatabase::class.java,
                "umcdb").addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                MIGRATION_6_7).build()

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