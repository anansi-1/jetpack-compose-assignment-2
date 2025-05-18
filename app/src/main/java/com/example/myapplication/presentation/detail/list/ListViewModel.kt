package com.example.myapplication.presentation.detail.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.TodoRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos = repository.cachedTodos
    val isLoading = repository.isLoading
    val errorMessage = repository.errorMessage

    init {
        // Load cache first, then try network update
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.fetchTodos(forceRefresh = true)
        }
    }
}
