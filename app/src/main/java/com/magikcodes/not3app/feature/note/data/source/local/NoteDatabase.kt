package com.magikcodes.not3app.feature.note.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magikcodes.not3app.feature.note.domain.model.Note
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
        @Volatile var INSTANCE: NoteDatabase? = null

        @Synchronized fun getInstance(context: Context): NoteDatabase =
            synchronized(this) {
                INSTANCE ?: synchronized(this) { buildDatabase(context).also { INSTANCE = it } }
            }

        private fun buildDatabase(@ApplicationContext appContext: Context): NoteDatabase =
            Room.databaseBuilder(
                appContext,
                NoteDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}