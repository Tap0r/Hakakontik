package com.example.hakakontik.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xakatonroomstipa.NotFavBase
import com.example.hakakontik.NotFavDao
import kotlinx.coroutines.launch
class ProfileViewModel(val dao: NotFavDao):ViewModel() {
    var newId:Long=0L

        fun addTask() {
            viewModelScope.launch {
                val news = NotFavBase()
                news.news_id = newId
                dao.insert(news)
            }
        }

}