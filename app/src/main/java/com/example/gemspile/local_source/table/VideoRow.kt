package com.example.gemspile.local_source.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoRow(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String
)