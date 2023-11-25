package arezou.ghorbani.s1_hilt.room.db

import arezou.ghorbani.s1_hilt.NOTE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = ""
)
