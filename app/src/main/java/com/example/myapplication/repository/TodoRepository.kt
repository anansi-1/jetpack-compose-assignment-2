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

    suspend fun fetchTodos() {
        _isLoading.value = true
        _errorMessage.value = null
        try {
            val remoteTodos = api.getTodos()
            dao.insertTodos(remoteTodos)
            _cachedTodos.value = remoteTodos
        } catch (e: Exception) {
            _errorMessage.value = "Failed to load todos"
            _cachedTodos.value = dao.getAllTodos() // fallback to local DB
        }
        _isLoading.value = false
    }

    suspend fun getTodoById(id: Int): Todo? {
        return dao.getAllTodos().find { it.id == id }
    }
}
