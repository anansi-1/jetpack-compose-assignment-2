package com.example.myapplication.repository

import com.example.myapplication.data.local.TodoDao
import com.example.myapplication.data.model.Todo
import com.example.myapplication.data.remote.TodoApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoRepository(
    private val api: TodoApiService,
    private val dao: TodoDao
) {
    private val _cachedTodos = MutableStateFlow<List<Todo>>(emptyList())
    val cachedTodos: StateFlow<List<Todo>> = _cachedTodos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    suspend fun fetchTodos(forceRefresh: Boolean = false) {
        _isLoading.value = true
        try {
            // Step 1: Load cached data first
            val localData = dao.getAllTodos()
            if (localData.isNotEmpty()) {
                _cachedTodos.value = localData
            }

            // Step 2: If forceRefresh or no cached data, try fetching from network
            if (forceRefresh || localData.isEmpty()) {
                try {
                    val remoteData = api.getTodos()
                    dao.insertTodos(remoteData)
                    _cachedTodos.value = remoteData
                    _errorMessage.value = null
                } catch (networkException: Exception) {
                    // Network failed: show offline message but keep cached data
                    if (localData.isEmpty()) {
                        // No data at all to show
                        _errorMessage.value = "No data available and you're offline."
                    } else {
                        _errorMessage.value = "You're offline. Showing cached data."
                    }
                }
            } else {
                // No refresh requested and cached data shown
                _errorMessage.value = null
            }

        } catch (e: Exception) {
            _cachedTodos.value = emptyList()
            _errorMessage.value = "Failed to load data."
        } finally {
            _isLoading.value = false
        }
    }



    suspend fun getTodoById(id: Int): Todo? {
        return dao.getAllTodos().find { it.id == id }
    }
}
