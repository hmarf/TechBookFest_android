package com.example.techbook.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity// (tableName = "user")
data class CircleDB(

    @PrimaryKey
    var uid: Int = 0,

    @ColumnInfo(name = "circleURL")
    var circleURL: String = "",

    @ColumnInfo(name = "circle")
    var circle: String = "",

    @ColumnInfo(name = "circleImage")
    var circleImage: String = "",

    @ColumnInfo(name = "arr")
    var arr: String = "",

    @ColumnInfo(name = "genere")
    var genere: String = "",

    @ColumnInfo(name = "keyword")
    var keyword: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "content")
    var content: String? = null
)
