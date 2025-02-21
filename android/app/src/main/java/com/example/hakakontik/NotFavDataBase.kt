package com.example.xakatonroomstipa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hakakontik.NotFavDao

@Database(entities = [NotFavBase::class], version = 1, exportSchema = false)
abstract class NotFavDataBase: RoomDatabase() {
    abstract val notfavDao: NotFavDao
    companion object {
        @Volatile
        private var INSTANCE: NotFavDataBase? = null
        fun getInstance(context: Context): NotFavDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotFavDataBase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}