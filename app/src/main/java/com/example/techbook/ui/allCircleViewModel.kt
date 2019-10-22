package com.example.techbook.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techbook.data.CircleEntityConverter
import com.example.techbook.data.api.entity.CircleEntity
import com.example.techbook.data.api.service.CircleInterface
import com.example.techbook.data.db.AppDatabase
import kotlinx.coroutines.launch
import java.lang.Exception

class AllCircleViewModel(
    private val circleInterface: CircleInterface,
    private val appDatabase: AppDatabase
) : ViewModel() {

    private val _allCircle = MutableLiveData<List<CircleEntity>>()
    val allCircle: LiveData<List<CircleEntity>>
        get() = _allCircle

    fun fetchAllCircle() = viewModelScope.launch {
        val result = circleInterface.circleAll().await()
        _allCircle.postValue(result.result)
    }

    fun insertLikeCircle(circle: CircleEntity) = viewModelScope.launch {
        appDatabase.userDao().insertNewData(CircleEntityConverter.toCircleDB(circle))
    }
}
