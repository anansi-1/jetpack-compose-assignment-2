package com.example.myapplication.presentation.detail.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    todos: List<Todo>,
    onTodoClick: (Int) -> Unit,
    viewModel: ListViewModel
) {
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.errorMessage.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("TODO List") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF344E72),
                titleContentColor = Color.White
            ),
            actions = {
                Button(
                    onClick = { viewModel.refresh() },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF627D98))
                ) {
                    Text("Refresh", color = Color.White)
                }
            }
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF344E72))
            }
        } else {
            if (error != null) {
                Text(
                    text = error,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = Color(0xFFD55C5C),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF0F4F8)),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(todos) { todo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .clickable { onTodoClick(todo.id) },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = todo.title,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF2E3A59)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Status: ${if (todo.completed) "Complete" else "Incomplete"}",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (todo.completed) Color(0xFF3B8D99) else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
