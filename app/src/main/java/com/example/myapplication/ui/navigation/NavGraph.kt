package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.repository.TodoRepository
import com.example.myapplication.ui.detail.DetailScreen
import com.example.myapplication.ui.detail.DetailViewModel
import com.example.myapplication.ui.list.ListScreen
import com.example.myapplication.ui.list.ListViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun NavGraph(startDestination: String = "list", repo: TodoRepository) {
    val navController = rememberNavController()
    val listViewModel = remember { ListViewModel(repo) }
    val detailViewModel = remember { DetailViewModel(repo) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("list") {
            val todosState = listViewModel.todos.collectAsState()

            ListScreen(
                todos = todosState.value,
                viewModel = listViewModel,
                onTodoClick = { navController.navigate("detail/$it") }
            )
        }

        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            detailViewModel.loadTodo(todoId) // Load the todo

            val todoState = detailViewModel.selectedTodo.collectAsState()

            DetailScreen(todo = todoState.value) {
                navController.popBackStack()
            }
        }

    }
}
