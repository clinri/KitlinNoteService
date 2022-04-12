package ru.netology.data

import java.time.Instant

data class Note(
    val id: Int = 0, //Идентификатор записи.
    val user_id: Int, //Идентификатор пользователя, создавшего заметку
    var title: String, //Заголовок заметки
    var text: String, //Текст заметки
    val date: Long = Instant.now().epochSecond, //Дата создания заметки в формате unixtime.
    var isDelete: Boolean = false //пометка удалено или нет
)
