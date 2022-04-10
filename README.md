# Мини-проект - NoteService
на основе Note VK (https://dev.vk.com/method/notes)

## дата классы
```
- Note (свойства):
id: Int
title: String
text: String
date: Long
isDelete: Boolean

- Comment (свойства):
id: Int
noteId: Int
message: String
date: Long
isDelete: Boolean
```

## Сервис (реализация логики работы с заметками).

- NoteServise (свойства)
```
- notes: MutableMap<Int, Note>
- noteCount: Int
- coments: MutableMap<Int, Comment>
- commentCount: Int
```
- NoteServise (методы)
```
- add(note: Note): Int
- get(): MutableMap<Int, Note>
```