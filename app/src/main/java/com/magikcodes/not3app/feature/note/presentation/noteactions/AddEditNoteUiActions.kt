package com.magikcodes.not3app.feature.note.presentation.noteactions

import androidx.compose.ui.focus.FocusState

interface AddEditNoteUiActions {
    data class EnteredTitle(val typedValue: String) : AddEditNoteUiActions
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteUiActions
    data class EnteredContent(val typedValue: String) : AddEditNoteUiActions
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteUiActions
    data class ChangeColor(val newColor: Int) : AddEditNoteUiActions
    object SaveNote : AddEditNoteUiActions
}