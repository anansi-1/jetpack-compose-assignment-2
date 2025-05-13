package com.example.myapplication.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Todo

@Composable
fun DetailScreen(
    todo: Todo?,
    onBack: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF5F5F5)) {
        todo?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Surface(
                        color = if (it.completed) Color(0xFFE0F2F1) else Color(0xFFFFEBEE),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (it.completed) "✓ Completed" else "✗ Incomplete",
                            color = if (it.completed) Color(0xFF388E3C) else Color(0xFFD32F2F),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Divider()

                Text("Description", fontWeight = FontWeight.SemiBold)
                Text(
                    text = "This is a detailed description for the todo item \"${it.title}\". It provides additional context and information about what needs to be done.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Details", fontWeight = FontWeight.SemiBold)
                Text("ID: ${it.id}")
                Text("User ID: ${it.userId}")
                Text(
                    text = "Status: ${if (it.completed) "Completed" else "Incomplete"}",
                    color = if (it.completed) Color(0xFF388E3C) else Color(0xFFD32F2F)
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
