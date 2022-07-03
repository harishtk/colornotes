package com.magikcodes.not3app.feature.note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magikcodes.not3app.feature.note.domain.model.Note
import com.magikcodes.not3app.feature.note.domain.usecase.NoteUseCases
import com.magikcodes.not3app.feature.note.domain.util.NoteOrder
import com.magikcodes.not3app.feature.note.domain.util.OrderType
import com.magikcodes.not3app.feature.note.presentation.util.NotesState
import com.magikcodes.not3app.feature.note.presentation.util.NotesUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf<NotesState>(NotesState())
    val uiState: State<NotesState> = _uiState

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onUiAction(action: NotesUiAction) {
        when (action) {
            is NotesUiAction.Order -> {
                if (uiState.value.noteOrder::class == action.noteOrder::class &&
                        uiState.value.noteOrder.orderType == action.noteOrder.orderType) {
                    return
                }
                getNotes(noteOrder = action.noteOrder)
            }
            is NotesUiAction.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(action.note)
                    recentlyDeletedNote = action.note
                }
            }
            is NotesUiAction.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesUiAction.ToggleOrderSection -> {
                _uiState.value = uiState.value.copy(
                    isOrderSectionVisible = !uiState.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _uiState.value = uiState.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}