package ru.netology.data

import java.time.Instant

data class Note(
    val id:Int =0, //Идентификатор заметки.
    val title:String, //Заголовок заметки
    val text:String, //Текст заметки
    val date: Long = Instant.now().epochSecond, //Дата создания заметки в формате unixtime.
)