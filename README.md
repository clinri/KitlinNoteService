# Мини-проект - NoteService
на основе API: Note VK (https://dev.vk.com/method/notes)

## Data классы

>### Note (свойства):
1. id: Int
2. title: String
3. text: String
4. date: Long
5. isDelete: Boolean
- неиспользованные свойства: privacy, comment_privacy, privacy_view, privacy_comment

>### Comment (свойства):
1. id: Int
2. noteId: Int
3. message: String
4. date: Long
5. isDelete: Boolean
- неиспользованные свойства: reply_to и guid.

## Сервис (реализация логики работы с заметками).

>### NoteServise (свойства)
- notes: MutableList<Note>
- noteCount: Int
- coments: MutableList<Comment>
- commentCount: Int

>### NoteServise (методы CRUD для Заметок)
- add(note: Note): Int
- get(vararg idNotes: Int, userId: Int, offset: Int = 0, count: Int, sort: Int = 0): MutableList<Note>
- getById(id: Int, owner_id:Int): Note
- edit(note: Note): Boolean
- delete(noteIdDelete: Int): Boolean

>### NoteServise (методы CRUD для Комментариев)
- createComment(comment: Comment): Int
- getComments(idNote: Int, userId: Int, offset: Int = 0, count: Int, sort: Int = 0): MutableList<Comment>
- editComment(comment: Comment): Boolean
- deleteComment(commentId: Int, owner_id: Int) : Int
- restoreComment(commentId: Int, owner_id: Int): Int