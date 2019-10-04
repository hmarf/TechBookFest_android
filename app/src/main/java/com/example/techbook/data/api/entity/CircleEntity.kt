package com.example.techbook.data.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CircleEntityResult (
    @Json(name = "result")
    val result: List<CircleEntity>
)

data class CircleEntity(
    @Json(name = "id")
    val id: Int,
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
