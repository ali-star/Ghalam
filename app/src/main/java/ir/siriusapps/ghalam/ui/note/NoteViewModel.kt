package ir.siriusapps.ghalam.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import ir.siriusapps.ghalam.model.NoteItem
import ir.siriusapps.ghalam.model.NoteItemMapper
import ir.siriusapps.ghalam.model.TextContentItem
import ir.sitiusapps.ghalam.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteItemMapper: NoteItemMapper
) : ViewModel() {

    private val _noteLiveDate = MutableLiveData<NoteItem>()
    val noteLiveData: LiveData<NoteItem> = _noteLiveDate

    val title = MutableLiveData<String>()

    fun newNote() {
        val note = NoteItem()
        val emptyTextContent = TextContentItem()
        note.contentList.add(emptyTextContent)
        _noteLiveDate.value = note
    }

    fun loadNote(noteLocalId: Long) {
        /*disposables.add(
            repository.getNote(noteLocalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {note ->
                        _noteLiveDate.value = note
                        note.title.let { title.value = it }
                    },
                    onError = {
                        // TODO handel the error may rise when getting a note
                    }
                )
        )*/
    }

    fun saveNote() {
        viewModelScope.launch {
            val note = noteLiveData.value
            note?.let {
                note.title = title.value
                saveNoteUseCase.save(noteItemMapper.mapToDomain(noteLiveData.value!!))
            }
        }
        /*val note = noteLiveData.value
        note?.let {
            note.title = title.value
            disposables.add(
                repository.saveNote(it).subscribeOn(Schedulers.io()).subscribe()
            )
        }*/
    }

}