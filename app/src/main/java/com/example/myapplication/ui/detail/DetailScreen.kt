package com.example.myapplication.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    Scaffold(
        topBar = {
            DetailTopBar(onBackClick = onBack)
        },
        containerColor = Color(0xFFF5F5F5)
    ) { padding ->
        todo?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Title and status
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
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

                // Description
                Text("Description", fontWeight = FontWeight.SemiBold)
                Text(
                    text = "This is a detailed description for the todo item \"${it.title}\". It provides additional context and information about what needs to be done.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Details
                Text("Details", fontWeight = FontWeight.SemiBold)
                Text("ID: ${it.id}")
                Text("User ID: ${it.userId}")
                Row {
                    Text("Status:")
                    Text(
                        text = " ${if (it.completed) "Completed" else "Incomplete"}",
                        color = if (it.completed) Color(0xFF388E3C) else Color(0xFFD32F2F)
                    )
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF5D4493))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text("Todo Details") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF5D4493),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}
