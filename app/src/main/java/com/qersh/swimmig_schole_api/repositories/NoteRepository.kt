package com.qersh.swimmig_schole_api.repositories

import androidx.annotation.WorkerThread
import com.qersh.swimmig_schole_api.dp.NoteDao
import com.qersh.swimmig_schole_api.model.note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<note>> = noteDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(notes: note) = withContext(IO) {
        noteDao.insert(notes)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(notes: String) = withContext(IO) {
        noteDao.deleteNote(notes)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun isExist(name: String): Boolean = withContext(IO) {
        return@withContext noteDao.isRowIsExist(name)
    }


}