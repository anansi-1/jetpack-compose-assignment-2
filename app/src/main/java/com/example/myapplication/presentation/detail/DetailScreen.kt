package com.example.myapplication.presentation.detail

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
        containerColor = Color(0xFFF0F4F8)
    ) { padding ->
        todo?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF344E72)
                    )
                    Surface(
                        color = if (it.completed) Color(0xFFE0F2F1) else Color(0xFFFDECEA),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = if (it.completed) "Completed" else "Incomplete",
                            color = if (it.completed) Color(0xFF3B8D99) else Color(0xFFD55C5C),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Divider(color = Color(0xFF344E72), thickness = 1.dp)

                Text("Title", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleMedium, color = Color(0xFF344E72))
                Text(
                    text = "\"${it.title}\"",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF2E3A59)
                )


                Text("Details", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleMedium, color = Color(0xFF344E72))
                Text("ID: ${it.id}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E3A59))
                Text("User ID: ${it.userId}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E3A59))
                Row {
                    Text("Status:", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E3A59))
                    Text(
                        text = " ${if (it.completed) "Completed" else "Incomplete"}",
                        color = if (it.completed) Color(0xFF3B8D99) else Color(0xFFD55C5C),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF344E72))
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
            containerColor = Color(0xFF344E72),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}
