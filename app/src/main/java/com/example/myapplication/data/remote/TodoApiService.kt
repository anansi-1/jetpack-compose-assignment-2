package com.example.myapplication.data.remote

import com.example.myapplication.data.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
