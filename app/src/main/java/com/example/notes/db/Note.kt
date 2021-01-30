package com.example.notes.db
import androidx.room.*

@Entity(tableName = "note_db")
class Note(
    val text:String
) {
    @PrimaryKey(autoGenerate = true) var id=0
}