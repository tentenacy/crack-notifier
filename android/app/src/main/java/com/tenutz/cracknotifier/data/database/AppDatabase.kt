package com.tenutz.cracknotifier.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import com.uytel.orgmanagement.util.converter.RoomConverters

@Database(
    entities = [
        Cracks.Crack::class,
        Cracks.CrackRemoteKeys::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }
}