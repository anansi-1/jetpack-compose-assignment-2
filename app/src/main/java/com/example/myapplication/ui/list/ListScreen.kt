package com.example.myapplication.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Todo

@Composable
fun ListScreen(viewModel: ListViewModel, onTodoClick: (Int) -> Unit) {
    val todos = viewModel.todos.collectAsState(initial = emptyList()).value
    val isLoading = viewModel.isLoading.collectAsState(initial = false).value
    val error = viewModel.errorMessage.collectAsState(initial = null).value

    Surface(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF5D4493))
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: $error", color = MaterialTheme.colorScheme.error)
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos) { todo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onTodoClick(todo.id) },
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F4F9))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = todo.title,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF5D4493)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (todo.completed) "Completed" else "Pending",
                                    color = if (todo.completed) Color(0xFF388E3C) else Color(0xFFE53935)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
