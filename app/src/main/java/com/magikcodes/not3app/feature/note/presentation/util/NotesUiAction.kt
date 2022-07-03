package com.magikcodes.not3app.feature.note.presentation.util

import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.util.NoteOrder

interface NotesUiAction {
    data class Order(val noteOrder: NoteOrder): NotesUiAction
    data class DeleteNote(val note: Note): NotesUiAction
    object RestoreNote: NotesUiAction
    object ToggleOrderSection: NotesUiAction
}