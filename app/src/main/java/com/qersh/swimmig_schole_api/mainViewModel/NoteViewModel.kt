package com.qersh.swimmig_schole_api.mainViewModel

import androidx.lifecycle.*
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repo: NoteRepository) : ViewModel() {

    var bool: Boolean = false
    val allNotes: LiveData<List<note>> = repo.allNotes.asLiveData()

    fun insert(notes: note) = viewModelScope.launch {
        repo.insert(notes)
        bool = true
    }

    fun delete(notes: String) = viewModelScope.launch {
        repo.delete(notes)
        bool = false
    }

    fun isExixst(name: String) = viewModelScope.launch {
        bool = repo.isExist(name)
    }

}


class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}