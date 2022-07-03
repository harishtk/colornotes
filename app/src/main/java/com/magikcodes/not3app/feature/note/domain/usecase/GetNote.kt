package com.magikcodes.not3app.feature.note.domain.usecase

import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNote(id)
    }
}