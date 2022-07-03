package com.magikcodes.not3app.feature.note.data.repository

import com.magikcodes.not3app.feature.note.data.source.local.NoteDao
import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNote(id: Int): Note? {
        return dao.getNote(id = id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note = note)
    }

}