package ir.siriusapps.ghalam.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.TextContent
import ir.siriusapps.ghalam.data.source.Repository
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _noteLiveDate = MutableLiveData<NoteAndContents>()
    val noteLiveData: LiveData<NoteAndContents> = _noteLiveDate

    fun start(noteLocalId: Long) {
        // default value of Long is zero and generated id's in room starts from 1,
        // so if we got zero here it means this is a new note
        if (noteLocalId > 0) {
            loadNote(noteLocalId)
        } else {
            newNote()
        }
    }

    private fun newNote() {
        val noteAndContents = NoteAndContents()
        val emptyTextContent = TextContent()
        noteAndContents.textContents.add(emptyTextContent)
        _noteLiveDate.value = noteAndContents
    }

    private fun loadNote(noteLocalId: Long) {
        disposables.add(
            repository.getNote(noteLocalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        _noteLiveDate.value = it
                    },
                    onError = {
                        // TODO handel the error may rise when getting a note
                    }
                )
        )
    }

    fun saveNote() {
        val note = noteLiveData.value?.note
        note?.contentList = noteLiveData.value!!.getContents()
        note?.let { repository.saveNote(it) }
    }

}