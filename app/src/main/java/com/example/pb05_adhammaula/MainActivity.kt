package com.example.pb05_adhammaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pb05_adhammaula.ui.theme.PB05_AdhamMaulaTheme
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = (applicationContext as CourseApplication).repository
            val viewModel: CourseViewModel = viewModel(
                factory = CourseViewModelFactory(repository)
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            PB05_AdhamMaulaTheme(darkTheme = uiState.isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    viewModel: CourseViewModel = viewModel(
        factory = CourseViewModelFactory(
            (LocalContext.current.applicationContext as CourseApplication).repository
        )
    )
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeRoute(
                onCourseClick = { id -> navController.navigate(Screen.Detail(id)) },
                onAddClick = { navController.navigate(Screen.Form(-1)) },
                viewModel = viewModel
            )
        }

        composable<Screen.Detail> { backStackEntry ->
            val route: Screen.Detail = backStackEntry.toRoute()
            val courseState by viewModel.getCourseById(route.courseId)
                .collectAsStateWithLifecycle(initialValue = null)

            DetailScreen(
                course = courseState,
                onBackClick = { navController.popBackStack() },
                onEditClick = { id -> navController.navigate(Screen.Form(id)) },
                onDeleteClick = { course ->
                    viewModel.deleteCourse(course)
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.Form> { backStackEntry ->
            val route: Screen.Form = backStackEntry.toRoute()
            val courseState by (if (route.courseId != -1) {
                viewModel.getCourseById(route.courseId)
            } else {
                flowOf(null)
            }).collectAsStateWithLifecycle(initialValue = null)

            FormScreen(
                course = courseState,
                onSaveClick = { code, name, sks, desc ->
                    viewModel.saveCourse(
                        id = if (route.courseId == -1) null else route.courseId,
                        code = code,
                        name = name,
                        sks = sks,
                        description = desc
                    )
                    navController.popBackStack()
                },
                onCancelClick = { navController.popBackStack() }
            )
        }
    }
}
