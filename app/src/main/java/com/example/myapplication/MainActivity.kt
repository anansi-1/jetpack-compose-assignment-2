package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.data.local.TodoDatabase
import com.example.myapplication.data.remote.RetrofitInstance
import com.example.myapplication.repository.TodoRepository
import com.example.myapplication.navigation.NavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables drawing behind system bars
        enableEdgeToEdge()

        val db = TodoDatabase.getDatabase(this)
        val repo = TodoRepository(RetrofitInstance.api, db.todoDao())

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = Color(0xFFEEECEC)
                ) {
                    NavGraph(repo = repo)
                }
            }
        }
    }
}
