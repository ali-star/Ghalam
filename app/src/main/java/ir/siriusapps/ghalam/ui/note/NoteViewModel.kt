package ir.siriusapps.ghalam.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ir.siriusapps.ghalam.model.NoteItem
import ir.siriusapps.ghalam.model.TextContentItem
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val gson: Gson
) : ViewModel() {

    private val _noteLiveDate = MutableLiveData<NoteItem>()
    val noteLiveData: LiveData<NoteItem> = _noteLiveDate

    val title = MutableLiveData<String>()

    fun start(noteLocalId: Long) {
        // default value of Long is zero and generated id's in room starts from one,
        // so if we got zero here it means this is a new note
        if (noteLocalId > 0) {
            loadNote(noteLocalId)
        } else {
            newNote()
        }
    }

    private fun newNote() {
        val note = NoteItem()
        val emptyTextContent = TextContentItem()
        note.contentList.add(emptyTextContent)
        _noteLiveDate.value = note
    }

    private fun loadNote(noteLocalId: Long) {
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
        /*val note = noteLiveData.value
        note?.let {
            note.title = title.value
            disposables.add(
                repository.saveNote(it).subscribeOn(Schedulers.io()).subscribe()
            )
        }*/
    }

}