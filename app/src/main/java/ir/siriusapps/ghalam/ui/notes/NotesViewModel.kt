package ir.siriusapps.ghalam.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.google.gson.Gson
import ir.siriusapps.ghalam.Event
import ir.siriusapps.ghalam.model.NoteItem
import ir.siriusapps.ghalam.model.NoteItemMapper
import ir.sitiusapps.ghalam.domain.usecase.GetNotesUseCase
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val noteItemMapper: NoteItemMapper,
    private val gson: Gson
) : ViewModel() {

    val notesLiveData: LiveData<PagedList<NoteItem>> = getNotesUseCase.get().map {
        noteItemMapper.mapToPresentation(it)
    }.toLiveData(60)

    private val _newNoteEvent = MutableLiveData<Event<Unit>>()
    val newNoteEvent: LiveData<Event<Unit>> = _newNoteEvent

    private val _openNoteEvent = MutableLiveData<Event<Long>>()
    val openNoteEvent: LiveData<Event<Long>> = _openNoteEvent

    fun addNewNote() {
        _newNoteEvent.value = Event(Unit)
    }

    fun openNote(noteLocalId: Long) {
        _openNoteEvent.value = Event((noteLocalId))
    }

}