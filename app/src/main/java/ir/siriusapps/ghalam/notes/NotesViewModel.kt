package ir.siriusapps.ghalam.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ir.siriusapps.ghalam.Event
import ir.siriusapps.ghalam.data.source.Repository
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _newNoteEvent = MutableLiveData<Event<Unit>>()
    val newNoteEvent: LiveData<Event<Unit>> = _newNoteEvent

    fun start() {
        disposables.add(repository.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val items = it
                },
                onError = {

                }
            ))
    }

    fun addNewNote() {
        _newNoteEvent.value = Event(Unit)
    }

}