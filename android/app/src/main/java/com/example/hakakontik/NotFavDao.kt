package com.example.hakakontik

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.xakatonroomstipa.NotFavBase

@Dao
interface NotFavDao {
    @Insert
    suspend fun insert(task: NotFavBase)
    @Insert
    suspend fun insertAll(tasks: List<NotFavBase>)
    @Update
    suspend fun update(task: NotFavBase)
    @Delete
    suspend fun delete(task: NotFavBase)
    @Query("SELECT * FROM news_table WHERE id = :id")
    fun get(id: Long): LiveData<NotFavBase>
    @Query("SELECT * FROM news_table ORDER BY id DESC")
    fun getAll(): LiveData<List<NotFavBase>>
}