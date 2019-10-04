package com.example.techbook.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.techbook.data.db.entity.CircleDB


@Dao
interface CircleDao {
    @Query("SELECT * FROM circledb")
    fun getAll(): LiveData<List<CircleDB>>

    @Insert
    suspend fun insertNewData(circle: CircleDB)

    @Query("DELETE FROM circledb WHERE uid = :id")
    suspend fun deleteData(id: Int)

    @Query("SELECT COUNT(*) FROM circledb WHERE uid = :id")
    suspend fun isEmpty(id: Int):Int

}