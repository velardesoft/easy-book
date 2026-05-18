package pe.edu.upc.easy_book.Presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(viewModel: HomeViewModel, navController: NavController) {
    val catalog by viewModel.catalog.collectAsState()
    val library by viewModel.library.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = tabIndex) {
            Tab(selected = tabIndex == 0, onClick = { tabIndex = 0 }, text = { Text("Libros") }) // Lista todos los libros [cite: 396]
            Tab(selected = tabIndex == 1, onClick = { tabIndex = 1 }, text = { Text("Mi Biblioteca") }) // Muestra libros leídos [cite: 397]
        }

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            val list = if (tabIndex == 0) catalog else library
            items(list) { book ->
                BookItem(book, isLibrary = tabIndex == 1, onDelete = { viewModel.removeBook(book.id) }) {
                    navController.navigate("detail/${book.id}")
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Book, isLibrary: Boolean, onDelete: () -> Unit, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clickable { onClick() }) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(model = book.image, contentDescription = null, modifier = Modifier.size(80.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text(book.author, style = MaterialTheme.typography.bodyMedium)
                Text("Año: ${book.publishedYear}", style = MaterialTheme.typography.bodySmall)
                if (isLibrary) Text("Leído: ${book.readDate}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }
            if (isLibrary) {
                IconButton(onClick = onDelete) { // El usuario podrá eliminar libros [cite: 424]
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}