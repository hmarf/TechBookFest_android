package com.example.techbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.techbook.data.db.AppDatabase
import com.example.techbook.data.db.dao.CircleDao
import com.example.techbook.data.db.entity.CircleDB
import kotlinx.coroutines.launch

class CircleViewModel (application: Application): AndroidViewModel(application){

    private val circleDao: CircleDao
    var db: AppDatabase? = null

    init {
        val db: AppDatabase = AppDatabase.get(application.applicationContext)
        circleDao = db.userDao()
    }

    fun insertData(circle: CircleDB){
        viewModelScope.launch {
            if( circleDao.isEmpty(circle.uid) == 0 ){
                circleDao.insertNewData(circle)
            }
        }
    }

    fun getLikeCircle():LiveData<List<CircleDB>>{
        return circleDao.getAll()
    }

    fun deleteData(id: Int){
        viewModelScope.launch {
            circleDao.deleteData(id)
        }
    }
}