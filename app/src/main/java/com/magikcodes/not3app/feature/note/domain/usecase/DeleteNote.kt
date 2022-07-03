package com.magikcodes.not3app.feature.note.domain.usecase

import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        return repository.deleteNote(note)
    }
}