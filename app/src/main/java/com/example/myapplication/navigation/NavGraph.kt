package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.repository.TodoRepository
import com.example.myapplication.presentation.detail.DetailScreen
import com.example.myapplication.presentation.detail.DetailViewModel
import com.example.myapplication.presentation.list.ListScreen
import com.example.myapplication.presentation.list.ListViewModel

@Composable
fun NavGraph(startDestination: String = "list", repo: TodoRepository) {
    val navController = rememberNavController()
    val listViewModel = remember { ListViewModel(repo) }


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
            val detailViewModel = remember(todoId) { DetailViewModel(repo, todoId) }
            val todoState = detailViewModel.selectedTodo.collectAsState()

            DetailScreen(todo = todoState.value) {
                navController.popBackStack()
            }
        }


    }
}
