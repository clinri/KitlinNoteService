package ru.netology.service

class NoteNotFoundExceptions(textException: String = "Error 180. Note not found") : Exception(textException)