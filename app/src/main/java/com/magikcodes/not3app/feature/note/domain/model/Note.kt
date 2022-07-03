package com.magikcodes.not3app.feature.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magikcodes.not3app.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink
        )
    }
}

class InvalidNoteException(override val message: String) : Exception()