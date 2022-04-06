package ru.netology.data

import java.time.Instant

data class Comment(
    val id: Int = 0, //Идентификатор комментария.
    val noteId:Int = 0, //Идентификатор заметки.
    val date: Long = Instant.now().epochSecond, // Дата создания комментария в формате Unixtime.
    val message : String //Текст комментария.
)
