package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note
import ru.netology.service.GetNotes.*

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
        notes[newId] = noteWithId
        return newId
    }

    // Reed
    // получение заметок по идентификаторам заметок, информацию о которых необходимо получить.
    fun get(vararg idNotes: Int): MutableList<Note> {
        val getNotes = mutableListOf<Note>()
        for (idNote in idNotes) {
            for ((index, value) in notes.withIndex()) {
                if (value.id == idNote) {
                    getNotes.add(notes[index])
                }
            }
        }
        return getNotes
    }


    fun get(param: Int, type: GetNotes): MutableList<Note> {
        var getNotes: MutableList<Note> = mutableListOf()
        when (type) {
            // получение заметок по идентификатору пользователя, информацию о заметках которого требуется получить.
            USER_ID -> {
                for ((index, value) in notes.withIndex()) {
                    if (value.user_id == param) {
                        getNotes.add(notes[index])
                    }
                }
                return getNotes
            }
            //получение заметок по cмещению (offset), необходимому для выборки определенного подмножества
            OFFSET -> {
                for (i in param - 1..notes.size) {
                    getNotes.add(notes[i])
                }
                return getNotes
            }
            COUNT -> {
                for (i in 0 until param) {
                    getNotes.add(notes[i])
                }
                return getNotes
            }
            SORT -> {
                if (param == 0) {//по дате создания в порядке убывания
                    getNotes.addAll(notes)
                    getNotes.sortBy { it.date }
                } else if (param == 1) {
                    getNotes.addAll(notes)
                    getNotes.sortBy { it.date }
                    getNotes.asReversed()
                }
            }
        }
        return getNotes
    }

    //получение заметок по cмещению (offset), необходимому для выборки определенного подмножества
    fun get(count: Int): MutableList<Note> {

    }


    fun getById(id: Int): Note = notes[id]

    // Update
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
        if (!notes[noteIdDelete].isDelete) {
            notes[noteIdDelete].isDelete = true // заметка отмечена как удаленная
            for ((index, value) in comments.withIndex()) {
                if (value.noteId == noteIdDelete) {
                    if (!value.isDelete)
                        comments[index].isDelete = true // комментарии к заметке отмечены как удаленные
                }
            }
            return true
        }
        return false // заметка уже отмечена как удаленная
    }

    /**     Comment (CRUD)     */
    // Create
    fun createComment(comment: Comment): Int {
        val newId = commentCount++
        val commentWithId: Comment = comment.copy(id = newId)
        comments[newId] = commentWithId
        return newId
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

    fun getComments(): MutableMap<Int, Comment> = comments

    fun removeAll() {
        notes.clear()
        noteCount = 1
        comments.clear()
        commentCount = 1
    }

    /** CRUD */
    override fun create(item: DataItem): Int {
        val thisItem = item as Note
        val newId = noteCount++
        val noteWithId: Note = thisItem.copy(id = newId)
        notes[newId] = noteWithId
        return newId
    }

    override fun read(id: Int): DataItem {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, item: DataItem) {
        TODO("Not yet implemented")
    }
}