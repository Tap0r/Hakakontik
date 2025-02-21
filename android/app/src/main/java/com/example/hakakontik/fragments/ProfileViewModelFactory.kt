package com.example.xakatonroomstipa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hakakontik.NotFavDao
import com.example.hakakontik.fragments.ProfileViewModel


class ProfileViewModelFactory(private val dao: NotFavDao) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}