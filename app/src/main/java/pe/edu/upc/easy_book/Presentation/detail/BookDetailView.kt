package pe.edu.upc.easy_book.Presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailView(bookId: Int, viewModel: BookDetailViewModel) {
    val book by viewModel.book.collectAsState()

    LaunchedEffect(bookId) { viewModel.loadBook(bookId) }

    Scaffold(topBar = { TopAppBar(title = { Text("Detalle") }) }) { padding ->
        book?.let { b ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                AsyncImage(model = b.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(b.title, style = MaterialTheme.typography.headlineMedium)
                Text(b.author, style = MaterialTheme.typography.titleMedium)
                Text("Editorial: ${b.editorial} | Género: ${b.genre}")
                Text("Calificación: ${b.rating} ⭐️")
                Spacer(modifier = Modifier.height(16.dp))
                Text(b.synopsis)
                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { viewModel.toggleRead() }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (b.isRead) "Desmarcar como Leído" else "Marcar como Leído") // Permite marcar/desmarcar [cite: 419]
                }
            }
        } ?: CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
}