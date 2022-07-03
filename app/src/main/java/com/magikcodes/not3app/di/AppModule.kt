package com.magikcodes.not3app.di

import android.app.Application
import com.magikcodes.not3app.feature.note.data.repository.NoteRepositoryImpl
import com.magikcodes.not3app.feature.note.data.source.local.NoteDatabase
import com.magikcodes.not3app.feature.note.domain.repository.NoteRepository
import com.magikcodes.not3app.feature.note.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase =
        NoteDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}
