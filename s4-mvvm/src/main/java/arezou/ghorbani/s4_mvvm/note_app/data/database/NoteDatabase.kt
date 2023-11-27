package arezou.ghorbani.s4_mvvm.note_app.data.database

import arezou.ghorbani.s4_mvvm.note_app.data.model.NoteEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}