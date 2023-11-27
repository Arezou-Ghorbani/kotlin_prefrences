package arezou.ghorbani.s3_mvi.note_app.viewmodel.main

import arezou.ghorbani.s3_mvi.note_app.data.model.NoteEntity

sealed class MainIntent {
    object LoadAllNotes : MainIntent()
    data class SearchNote(val search: String) : MainIntent()
    data class FilterNote(val filter: String) : MainIntent()
    data class DeleteNote(val entity: NoteEntity) : MainIntent()
    data class ClickToDetail(val id: Int) : MainIntent()
}
