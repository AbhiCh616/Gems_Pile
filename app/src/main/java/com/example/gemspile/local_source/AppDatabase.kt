package com.example.gemspile.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gemspile.local_source.dao.VideoDao
import com.example.gemspile.local_source.table.VideoRow

@Database(
    version = 1,
    entities = [VideoRow::class],
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDao
}