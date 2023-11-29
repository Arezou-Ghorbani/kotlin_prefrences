package arezou.ghorbani.s3_mvp.room.ui.main

import arezou.ghorbani.s3_mvp.base.BasePresenter
import arezou.ghorbani.s3_mvp.room.data.model.NoteEntity

interface MainContracts {

    interface View {
        fun showAllNotes(notes: List<NoteEntity>)
        fun showEmpty()
        fun deleteMessage()
    }

    interface Presenter : BasePresenter {
        fun loadAllNotes()
        fun deleteNote(entity: NoteEntity)
        fun filterNote(priority: String)
        fun searchNote(title: String)
    }
}