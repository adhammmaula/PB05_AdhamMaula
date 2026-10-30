package com.example.pb05_adhammaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pb05_adhammaula.ui.theme.PB05_AdhamMaulaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PB05_AdhamMaulaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(
                courses = CourseRepository.courses,
                onCourseClick = { id -> navController.navigate(Screen.Detail(id)) },
                onAddClick = { navController.navigate(Screen.Form(-1)) }
            )
        }

        composable<Screen.Detail> { backStackEntry ->
            val route: Screen.Detail = backStackEntry.toRoute()
            val course = CourseRepository.getCourseById(route.courseId)

            DetailScreen(
                course = course,
                onBackClick = { navController.popBackStack() },
                onEditClick = { id -> navController.navigate(Screen.Form(id)) }
            )
        }

        composable<Screen.Form> { backStackEntry ->
            val route: Screen.Form = backStackEntry.toRoute()
            val course = CourseRepository.getCourseById(route.courseId)

            FormScreen(
                course = course,
                onSaveClick = { code, name, sks, desc ->
                    CourseRepository.addOrUpdateCourse(
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