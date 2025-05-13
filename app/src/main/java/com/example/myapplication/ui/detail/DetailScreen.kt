package com.example.myapplication.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(viewModel: DetailViewModel, todoId: Int, onBack: () -> Unit) {
    val todo = viewModel.selectedTodo.collectAsState().value

    LaunchedEffect(todoId) {
        viewModel.loadTodo(todoId)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        todo?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Todo Details",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF5D4493),
                    fontWeight = FontWeight.Bold
                )

                Text(text = "Title: ${it.title}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "ID: ${it.id}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "User ID: ${it.userId}", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "Status: ${if (it.completed) "Completed" else "Pending"}",
                    color = if (it.completed) Color(0xFF388E3C) else Color(0xFFE53935),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D4493))
                ) {
                    Text("Back", color = Color.White)
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF5D4493))
        }
    }
}
