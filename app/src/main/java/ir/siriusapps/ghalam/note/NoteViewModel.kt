package ir.siriusapps.ghalam.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ir.siriusapps.ghalam.data.Content
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.TextContent
import ir.siriusapps.ghalam.data.source.Repository
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _noteLiveDate = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note> = _noteLiveDate

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
        val note = Note()
        val emptyTextContent = TextContent()
        note.contentList.add(emptyTextContent)
        _noteLiveDate.value = note
    }

    private fun loadNote(noteLocalId: Long) {
        disposables.add(
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
        )
    }

    fun saveNote() {
        val note = noteLiveData.value
        note?.let {
            note.title = title.value
            disposables.add(
                repository.saveNote(it).subscribeOn(Schedulers.io()).subscribe()
            )
        }
    }

}