package com.example.notes.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class NoteRepo(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> =noteDao.getAllNote()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note)
    }
}