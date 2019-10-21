package ir.siriusapps.ghalam.data.source

import io.reactivex.Completable
import io.reactivex.Single
import ir.siriusapps.ghalam.data.Label

interface LabelDataSource {

    fun saveLabel(label: Label): Completable

    fun getLabels(): Single<List<Label>>

    fun deleteLabel(localId: Long): Completable

}