package arezou.ghorbani.s1_hilt.room.db

import arezou.ghorbani.s1_hilt.NOTE_TABLE
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: NoteModel)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNote(): MutableList<NoteModel>
}