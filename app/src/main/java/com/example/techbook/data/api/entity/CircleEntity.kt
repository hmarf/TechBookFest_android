package com.example.techbook.data.api.entity

import com.squareup.moshi.Json

data class CircleEntity(
    @Json(name = "CircleURL")
    val circleURL: String,
    @Json(name = "Circle")
    val circle: String,
    @Json(name = "CircleImage")
    val circleImage: String,
    @Json(name = "arr")
    val arr: String,
    @Json(name = "Genere")
    val genere: String,
    @Json(name = "Keyword")
    val keyword: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Content")
    val content: String?
)
