package ru.netology.data

import java.time.Instant

data class Comment(
    private val id: Int = 0, //Идентификатор комментария.
    private val noteId:Int = 0,
    private val from_id: Int = 0, //Идентификатор автора комментария.
    private val date: Long = Instant.now().epochSecond, // Дата создания комментария в формате Unixtime.

)
