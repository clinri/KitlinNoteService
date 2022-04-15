package ru.netology.service

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.netology.data.Comment
import ru.netology.data.Note
import ru.netology.service.NoteServise

class NoteServiseTest {
    @Before
    fun create_notes() {
        fillNotes()
        fillComments()
    }

    @After
    fun showMaps() {
        NoteServise.removeAll()
    }

    private fun fillNotes() {
        NoteServise.add(Note(title = "Kotlin", text = "Kotlin is ...", user_id = 1))
        NoteServise.add(Note(title = "Java", text = "Java is ...", user_id = 1))
    }

    private fun fillComments() {
        NoteServise.createComment(Comment(noteId = 1, user_id = 2, message = "I agree"))
        NoteServise.createComment(Comment(noteId = 1, user_id = 3, message = "I like it"))
        NoteServise.createComment(Comment(noteId = 2, user_id = 2, message = "I agree"))
        NoteServise.createComment(Comment(noteId = 2, user_id = 3, message = "I like it"))
    }

    @Test
    fun test_add_id_in_note() {
        val id = NoteServise.add(Note(title = "VB", text = "VB is ...", user_id = 111))
        Assert.assertEquals(id, 3)
    }

    @Test
    fun test_get_notes_whith_sort() {
        val notes = NoteServise.get(1, 2, userId = 1, sort = 1, count = 10)
        Assert.assertEquals(notes[0].id, 2)
    }

    @Test
    fun test_getById_note_title() {
        val note = NoteServise.getById(1, 1)
        Assert.assertEquals(note.title, "Kotlin")
    }

    @Test
    fun test_edit_note_by_id() {
        val noteEdit = Note(id = 1, title = "Basic", text = "Basic is ...", user_id = 1)
        NoteServise.edit(noteEdit)
        val noteTest = NoteServise.getById(1, 1)
        Assert.assertEquals(noteTest.title, noteEdit.title)
    }

    @Test
    fun test_delete_exist_note() {
        NoteServise.delete(1)
        Assert.assertTrue(NoteServise.getById(1, 1).isDelete)
    }

    @Test(expected = ItemAlreadyDeleteException::class)
    fun test_delete_already_delete_note() {
        NoteServise.delete(1)
        NoteServise.delete(1)
    }

    @Test
    fun test_add_id_in_created_comment() {
        val id = NoteServise.createComment(Comment(noteId = 2, user_id = 2, message = "I try to create comment here"))
        Assert.assertEquals(id, 5)
    }

    @Test
    fun test_get_comments_whit_sort() {
        val comments = NoteServise.getComments(1, 3, sort = 1, count = 10)
        Assert.assertEquals(comments.last().id, 2)
    }

    @Test
    fun test_edit_comment() {
        val commentEdit = Comment(id = 1, noteId = 1, user_id = 2, message = "I try to edit my comment")
        NoteServise.editComment(commentEdit)
        val commentTest = NoteServise.getCommentById(1)
        Assert.assertEquals(commentTest.message, commentEdit.message)
    }

    @Test(expected = ItemAlreadyDeleteException::class)
    fun test_edit_already_deleted_comment() {
        NoteServise.deleteComment(1,2)
        val commentEdit = Comment(id = 1, noteId = 1, user_id = 2, message = "I try to edit my deleted comment")
        NoteServise.editComment(commentEdit)
    }

    @Test
    fun test_delete_comment() {
        NoteServise.deleteComment(1, 2)
        Assert.assertTrue(NoteServise.getCommentById(1).isDelete)
    }

    @Test
    fun test_restore_comment() {
        NoteServise.deleteComment(1, 2)
        Assert.assertEquals(NoteServise.restoreComment(1, 2),1)
    }
}