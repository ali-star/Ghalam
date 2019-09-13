package ir.siriusapps.ghalam.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.siriusapps.ghalam.Event
import javax.inject.Inject

class NotesViewModel @Inject constructor() : ViewModel() {

    private val _newNoteEvent = MutableLiveData<Event<Unit>>()
    val newNoteEvent: LiveData<Event<Unit>> = _newNoteEvent

    fun addNewNote() {
        _newNoteEvent.value = Event(Unit)
    }

}