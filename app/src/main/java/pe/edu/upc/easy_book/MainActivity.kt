package pe.edu.upc.easy_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.upc.easy_book.Presentation.detail.BookDetailView
import pe.edu.upc.easy_book.Presentation.detail.BookDetailViewModel
import pe.edu.upc.easy_book.Presentation.home.HomeView
import pe.edu.upc.easy_book.Presentation.home.HomeViewModel
import pe.edu.upc.easy_book.ui.theme.EasybookTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasybookTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            val vm: HomeViewModel = hiltViewModel()
                            HomeView(vm, navController)
                        }
                        composable("detail/{bookId}") { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull()
                            if (bookId != null) {
                                val vm: BookDetailViewModel = hiltViewModel()
                                BookDetailView(bookId, vm)
                            }
                        }
                    }
                }
            }
        }
    }
}