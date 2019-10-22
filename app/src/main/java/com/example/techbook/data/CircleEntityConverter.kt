package com.example.techbook.data

import com.example.techbook.data.api.entity.CircleEntity
import com.example.techbook.data.db.entity.CircleDB

object CircleEntityConverter {

    fun toCircleDB(circleEntity: CircleEntity) =
        CircleDB(
            uid = circleEntity.id,
            circleURL = circleEntity.circleURL,
            circle = circleEntity.circle,
            circleImage = circleEntity.circleImage,
            arr = circleEntity.arr,
            genere = circleEntity.genere,
            keyword = circleEntity.keyword,
            title = circleEntity.title,
            content = circleEntity.content
        )
}
