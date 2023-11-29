package arezou.ghorbani.s3_mvp.room.ui.add

import arezou.ghorbani.s3_mvp.base.BasePresenter
import arezou.ghorbani.s3_mvp.room.data.model.NoteEntity

interface NoteContracts {

    interface View {
        fun close()
        fun loadNote(note: NoteEntity)
    }

    interface Presenter : BasePresenter {
        fun saveNote(entity: NoteEntity)
        fun detailNote(id: Int)
        fun updateNote(entity: NoteEntity)
    }
}