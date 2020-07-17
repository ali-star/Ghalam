package ir.sitiusapps.ghalam.domain.usecase

import ir.sitiusapps.ghalam.domain.model.Note
import ir.sitiusapps.ghalam.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend fun save(note: Note): Long = withContext(Dispatchers.IO) {
        return@withContext notesRepository.saveNote(note)
    }

}