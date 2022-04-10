import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.netology.data.Note
import ru.netology.service.NoteServise

class TestNoteServise {
    @Before
    fun create_notes() {
        NoteServise.fillNotes()
        NoteServise.fillComments()
    }

    @Test
    fun test_add_id_in_note() {
        val id = NoteServise.add(Note(title = "VB", text = "VB is ..."))
        Assert.assertEquals(id, 3)
    }

    @Test
    fun test_delete_note() {
        Assert.assertTrue(NoteServise.delete(1))
    }

    @Test
    fun test_delete_comment(){
        Assert.assertTrue(NoteServise.deleteComment(1))
    }

    @After
    fun showMaps(){
        println(NoteServise.get())
        println(NoteServise.getComments())
        NoteServise.removeAll()
    }
}