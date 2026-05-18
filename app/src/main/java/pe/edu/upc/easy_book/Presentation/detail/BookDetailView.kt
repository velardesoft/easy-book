package pe.edu.upc.easy_book.Presentation.detail

import androidx.compose.runtime.Composable

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