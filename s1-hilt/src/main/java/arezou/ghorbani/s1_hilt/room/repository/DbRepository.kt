package arezou.ghorbani.s1_hilt.room.repository

import arezou.ghorbani.s1_hilt.room.db.NoteDao
import arezou.ghorbani.s1_hilt.room.db.NoteModel
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(entity: NoteModel) = dao.saveNote(entity)
    fun getAllNotes() = dao.getAllNote()
}