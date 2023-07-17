package com.example.a160420124_marcelladivaviorent_healthcareumc.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
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
        MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build()
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

fun ImageView.loadImage(url: String?, progressBar:ProgressBar) {

    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object:Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
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