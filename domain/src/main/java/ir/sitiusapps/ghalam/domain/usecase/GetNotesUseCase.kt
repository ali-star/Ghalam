package ir.sitiusapps.ghalam.domain.usecase

import androidx.paging.DataSource
import ir.sitiusapps.ghalam.domain.model.Note
import ir.sitiusapps.ghalam.domain.repository.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    fun get(): DataSource.Factory<Int, Note> {
        return notesRepository.getNotes()
    }

}