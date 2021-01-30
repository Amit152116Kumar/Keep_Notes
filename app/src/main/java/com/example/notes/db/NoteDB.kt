package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1,exportSchema = false)

abstract class NoteDB: RoomDatabase() {

    abstract fun getNoteDao() :NoteDao

    companion object{

        @Volatile
        private var Instance:NoteDB?=null

        fun getDatabase(context: Context):NoteDB{
            return Instance?: synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,NoteDB::class.java,"note_database")
                    .build()
                Instance=instance
                instance
            }
        }
    }
}
