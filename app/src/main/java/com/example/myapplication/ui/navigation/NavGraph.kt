package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.repository.TodoRepository
import com.example.myapplication.ui.detail.DetailScreen
import com.example.myapplication.ui.detail.DetailViewModel
import com.example.myapplication.ui.list.ListScreen
import com.example.myapplication.ui.list.ListViewModel

@Composable
fun NavGraph(startDestination: String = "list", repo: TodoRepository) {
    val navController = rememberNavController()
    val listViewModel = remember { ListViewModel(repo) }
    val detailViewModel = remember { DetailViewModel(repo) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("list") {
            ListScreen(viewModel = listViewModel, onTodoClick = {
                navController.navigate("detail/$it")
            })
        }
        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toInt() ?: 0
            DetailScreen(viewModel = detailViewModel, todoId = todoId) {
                navController.popBackStack()
            }
        }
    }
}
