package arezou.ghorbani.s3_mvi.note_app.data.repository

import arezou.ghorbani.s3_mvi.note_app.data.database.NoteDao
import arezou.ghorbani.s3_mvi.note_app.data.model.NoteEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(private val dao: NoteDao) {
    suspend fun saveNote(entity: NoteEntity) = dao.saveNote(entity)
    suspend fun updateNote(entity: NoteEntity) = dao.updateNote(entity)
    fun getNote(id: Int) = dao.getNote(id)
}