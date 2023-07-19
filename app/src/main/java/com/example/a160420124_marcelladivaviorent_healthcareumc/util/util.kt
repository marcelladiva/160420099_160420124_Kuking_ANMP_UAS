package com.example.a160420124_marcelladivaviorent_healthcareumc.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.UMCDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

val DB_NAME = "umcdb"

fun buildDb(context: Context):UMCDatabase {
    val db = Room.databaseBuilder(context, UMCDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8).build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE article ('id' TEXT, 'name' TEXT, 'photoUrl' TEXT, 'detail' TEXT,'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE facility ('id' TEXT, 'name' TEXT, 'photoUrl' TEXT, 'description' TEXT,'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE drug ('id' TEXT, 'name' TEXT, 'photoUrl' TEXT, 'detail' TEXT, 'dosis' TEXT, 'harga' TEXT,'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE user ('id' TEXT, 'username' TEXT, 'password' TEXT, 'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE history ('id' TEXT, 'userId' TEXT, 'doctorName' TEXT, 'hari' TEXT, 'jam' TEXT, 'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE history_ser ('userId' TEXT, 'doctorName' TEXT, 'hari' TEXT, 'jam' TEXT, 'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
        database.execSQL("DROP TABLE history")
        database.execSQL("ALTER TABLE history_ser RENAME TO history")
    }
}

val MIGRATION_7_8 = object : Migration(7, 8) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE user_ser ('username' TEXT, 'password' TEXT, 'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
        database.execSQL("DROP TABLE user")
        database.execSQL("ALTER TABLE user_ser RENAME TO user")
    }
}

@BindingAdapter("photoUrl")
fun loadImage(view: View, photoUrl: String?) {
    val image: ImageView = view as ImageView
    Picasso.get().load(photoUrl).into(view)
}

fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}