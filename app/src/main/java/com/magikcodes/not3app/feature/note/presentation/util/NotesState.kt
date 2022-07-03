package com.magikcodes.not3app.feature.note.presentation.util

import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.util.NoteOrder
import com.magikcodes.not3app.feature.note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
