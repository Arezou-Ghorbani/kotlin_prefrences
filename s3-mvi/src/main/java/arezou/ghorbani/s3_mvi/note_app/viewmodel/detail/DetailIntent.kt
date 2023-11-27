package arezou.ghorbani.s3_mvi.note_app.viewmodel.detail

import arezou.ghorbani.s3_mvi.note_app.data.model.NoteEntity

sealed class DetailIntent {
    object SpinnersList : DetailIntent()
    data class SaveNote(val entity: NoteEntity) : DetailIntent()
    data class UpdateNote(val entity: NoteEntity) : DetailIntent()
    data class NoteDetail(val id: Int) : DetailIntent()
}
