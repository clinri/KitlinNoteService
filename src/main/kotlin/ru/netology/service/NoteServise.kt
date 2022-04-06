package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note

object NoteServise {
    private var notes: MutableMap<Int, Note> = HashMap()
    private var noteCount: Int = 1
    private var comments: MutableMap<Int, Comment> = HashMap()
    private var commentCount: Int = 1

    fun add(note: Note): Int {
        val id = noteCount++
        val noteWithId: Note = note.copy(id = id)
        notes[id] = noteWithId
        return id
    }

    fun delete(noteId: Int): Boolean = notes.remove(noteId) != null

    fun createComment(comment: Comment): Int {
        val id = commentCount++
        val commentWithId: Comment = comment.copy(id = id)
        comments[id] = commentWithId
        return id
    }

    fun deleteComment(commentId: Int) = comments.remove(commentId) != null

    fun fillNotes() {
        add(Note(title = "Kotlin", text = "Kotlin is ..."))
        add(Note(title = "Java", text = "Java is ..."))
    }

    fun fillComments() {
        createComment(Comment(noteId = 1, message = "I agree"))
        createComment(Comment(noteId = 1, message = "I like it"))
    }

    fun getNotes(): MutableMap<Int, Note> = notes
    fun getComments(): MutableMap<Int, Comment> = comments
    fun removeAll(){
        notes.clear()
        noteCount = 1
        comments.clear()
        commentCount = 1
    }
}