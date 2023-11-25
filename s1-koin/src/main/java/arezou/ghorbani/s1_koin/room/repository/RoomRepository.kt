package arezou.ghorbani.s1_koin.room.repository

import arezou.ghorbani.s1_koin.room.db.NoteDao
import arezou.ghorbani.s1_koin.room.db.NoteModel

class RoomRepository(private val dao: NoteDao) {
    suspend fun saveNote(note: NoteModel) = dao.saveNote(note)
    fun getAllNotes() = dao.getAllNote()
}

/*
val repositoryModule = module {
    factory { RoomRepository(get()) }
}*/
