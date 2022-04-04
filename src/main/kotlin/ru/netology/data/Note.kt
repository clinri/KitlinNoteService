package ru.netology.data

import java.time.Instant

data class Note(
    private val id:Int =0, //Идентификатор заметки.
    private val title:String, //Заголовок заметки
    private val text:String, //Текст заметки
    private val date: Long = Instant.now().epochSecond, //Дата создания заметки в формате unixtime.
)