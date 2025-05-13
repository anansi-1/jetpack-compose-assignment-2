package com.example.myapplication.ui.list

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
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("TODO List") },
            actions = {
                Button(
                    onClick = {  },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF37160))
                ) {
                    Text("Refresh", color = Color.White)
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(todos) { todo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clickable { onTodoClick(todo.id) },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = todo.title,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Status: ${if (todo.completed) "Complete" else "Incomplete"}",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (todo.completed) Color(0xFF388E3C) else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
