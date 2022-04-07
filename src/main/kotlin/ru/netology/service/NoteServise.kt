package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note

object NoteServise {
    private var notes: MutableMap<Int, Note> = HashMap()
    private var noteCount: Int = 1
    private var comments: MutableMap<Int, Comment> = HashMap()
    private var commentCount: Int = 1

    /**     Note (CRUD)     */
    // Create

    fun add(note: Note): Int {
        val id = noteCount++
        val noteWithId: Note = note.copy(id = id)
        notes[id] = noteWithId
        return id
    }

    // Reed

    // Update

    // Delete
    fun delete(noteIdDelete: Int): Boolean {
        if (notes[noteIdDelete] != null) {
            if (!notes[noteIdDelete]!!.isDelete) {
                notes[noteIdDelete]!!.isDelete = true
                for (comment in comments) {
                    val (noteId) = comment
                    if (noteId == noteIdDelete) {
                        comments[comment.key]!!.isDelete = true
                    }
                }
                return true
            } else {
                return false
            }
        }
        return false
    }


    /**     Comment (CRUD)     */
    // Create
    fun createComment(comment: Comment): Int {
        val id = commentCount++
        val commentWithId: Comment = comment.copy(id = id)
        comments[id] = commentWithId
        return id
    }

    // Reed

    // Update

    // Delete
    fun deleteComment(commentId: Int) = comments.remove(commentId) != null

    /**     other functions     */
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

    fun removeAll() {
        notes.clear()
        noteCount = 1
        comments.clear()
        commentCount = 1
    }
}