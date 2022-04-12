package ru.netology.data

import java.time.Instant

data class Comment(
    val id: Int = 0, //Идентификатор комментария.
    val noteId: Int, //Идентификатор заметки к которой относится данный комментарий.
    val user_id: Int, //Идентификатор пользователя, создавшего комментарий
    var message: String, //Текст комментария.
    val date: Long = Instant.now().epochSecond, //Дата создания комментария в формате unixtime.
    var isDelete: Boolean = false //пометка удалено или нет
)