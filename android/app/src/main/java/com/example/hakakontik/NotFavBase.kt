package com.example.xakatonroomstipa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NotFavBase(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    @ColumnInfo(name = "news_id")
    var news_id:Long = 0L,
    @ColumnInfo(name = "news_type")
    var news_type:Long = 0L,
)