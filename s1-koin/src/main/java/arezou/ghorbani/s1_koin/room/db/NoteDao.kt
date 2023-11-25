package arezou.ghorbani.s1_koin.room.db

import arezou.ghorbani.s1_koin.NOTE_TABLE
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(item: NoteModel)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNote(): LiveData<List<NoteModel>>
}