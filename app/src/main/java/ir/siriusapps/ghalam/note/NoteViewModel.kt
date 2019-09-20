package ir.siriusapps.ghalam.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.source.Repository
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _noteLiveDate = MutableLiveData<NoteAndContents>()
    val noteLiveData: LiveData<NoteAndContents> = _noteLiveDate

    fun start(noteLocalId: Long?) {
        noteLocalId?.let {
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
    }

    fun saveNote() {

    }

}