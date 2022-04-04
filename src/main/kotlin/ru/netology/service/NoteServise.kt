package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note

object NoteServise {
    private var notes: MutableMap<Int, Note> = HashMap()
    private var noteCount: Int = 0
    private val comment = emptySet<Comment>()
    private val commentCount: Int = 0

    fun add(note: Note): Note {
        val id = noteCount++
        val noteWithId: Note = note.copy(id = id)
        notes[id] = noteWithId
        return noteWithId
    }
}