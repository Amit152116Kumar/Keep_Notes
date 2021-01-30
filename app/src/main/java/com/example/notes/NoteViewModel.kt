package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.db.Note
import com.example.notes.db.NoteDB
import com.example.notes.db.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes:Flow<List<Note>>
    private val repo:NoteRepo
    init {
        val dao=NoteDB.getDatabase(application).getNoteDao()
        repo=NoteRepo(dao)
        allNotes=repo.allNotes

    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(note)
        }
    }

    fun insertNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(note)
        }
    }
}