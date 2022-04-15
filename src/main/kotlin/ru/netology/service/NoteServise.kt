package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note

object NoteServise {
    private var notes: MutableList<Note> = mutableListOf()
    private var noteCount: Int = 1
    private var comments: MutableList<Comment> = mutableListOf()
    private var commentCount: Int = 1

    /**     Note (CRUD)     */
    // Create
    fun add(note: Note): Int {
        val newId = noteCount++
        val noteWithId: Note = note.copy(id = newId)
        notes.add(noteWithId)
        return newId
    }

    // Read
    // получение заметок по идентификаторам заметок, информацию о которых необходимо получить.
    fun get(vararg idNotes: Int, userId: Int, offset: Int = 0, count: Int, sort: Int = 0): MutableList<Note> {
        var getNotes: MutableList<Note> = mutableListOf()
        val n = 0
        for (idNote in idNotes) {
            for (value in notes) {
                if ((value.id == idNote) && (value.user_id == userId)) {
                    if (n >= offset) {
                        getNotes.add(value)
                    }
                    if (getNotes.size == count) {
                        break
                    }
                }
            }
        }
        when (sort) {
            0 -> {
                getNotes.sortedWith(compareBy { it.date })
            }
            1 -> {
                getNotes.sortedWith(compareBy { it.date })
                getNotes = getNotes.asReversed()
            }
        }
        return getNotes.ifEmpty {
            throw ItemNotFoundExceptions()
        }
    }

    // получение заметок по идентификатору заметки, и идентификатору владельца заметки
    fun getById(id: Int, owner_id: Int): Note {
        for (note in notes) {
            if (note.id == id && note.user_id == owner_id)
                return note
        }
        throw ItemNotFoundExceptions()
    }

    // Update
    // редактирование заметки
    fun edit(note: Note): Boolean {
        for ((index, value) in notes.withIndex()) {
            if (value.id == note.id) {
                notes[index] = note.copy(date = value.date)
                return true
            }
        }
        return false
    }

    // Delete
    fun delete(noteIdDelete: Int): Boolean {
        for ((index, value) in notes.withIndex()) {
            if (value.id == noteIdDelete) {
                if (value.isDelete) {
                    throw ItemAlreadyDeleteException("Вы пытаетесь удалить заметку которая уже удалена")
                }
                notes[index] = value.copy(isDelete = true) // заметка отмечена как удаленная
                return true
            }
        }
        return false // заметка не найдена
    }

    /**     Comment (CRUD)     */
    // Create
    fun createComment(comment: Comment): Int {
        val newId = commentCount++
        val commentWithId: Comment = comment.copy(id = newId)
        comments.add(commentWithId)
        return newId
    }

    // Read
    // получение комментариев к заметке по идентификаторам заметок.
    fun getComments(idNote: Int, userId: Int, offset: Int = 0, count: Int, sort: Int = 0): MutableList<Comment> {
        var getComments: MutableList<Comment> = mutableListOf()
        val n = 0
        for (value in comments) {
            if ((value.noteId == idNote) && (value.user_id == userId)) {
                if (n >= offset) {
                    getComments.add(value)
                }
                if (getComments.size == count) {
                    break
                }
            }
        }
        when (sort) {
            0 -> {
                getComments.sortedWith(compareBy { it.date })
            }
            1 -> {
                getComments.sortedWith(compareBy { it.date })
                getComments = getComments.asReversed()
            }
        }
        return getComments.ifEmpty {
            throw ItemNotFoundExceptions()
        }
    }

    fun getCommentById(id: Int): Comment {
        for (comment in comments) {
            if (comment.id == id)
                return comment
        }
        throw ItemNotFoundExceptions("Comment not found")
    }

    // Update
    fun editComment(comment: Comment): Boolean {
        for ((index, value) in comments.withIndex()) {
            if (value.id == comment.id) {
                if (value.isDelete) {
                    throw ItemAlreadyDeleteException("Вы пытаетесь отредактировать удаленный комментарий")
                }
                comments[index] = comment.copy(date = value.date)
                return true
            }
        }
        return false
    }

    // Delete
    fun deleteComment(commentId: Int, owner_id: Int): Int {
        for ((index, value) in comments.withIndex()) {
            if (value.id == commentId && value.user_id == owner_id) {
                if (value.isDelete) {
                    throw ItemAlreadyDeleteException("Вы пытаетесь удалить комментарий, который уже был удален")
                }
                comments[index] = value.copy(isDelete = true)
                return 1
            }
        }
        return 0
    }

    fun restoreComment(commentId: Int, owner_id: Int): Int {
        for ((index, value) in comments.withIndex()) {
            if (value.id == commentId && value.user_id == owner_id) {
                if (value.isDelete){
                    return 1
                }
                comments[index] = value.copy(isDelete = false)
                return 0
            }
        }
        return 0
    }

    /**     other functions     */
    fun removeAll() {
        notes.clear()
        noteCount = 1
        comments.clear()
        commentCount = 1
    }
}