package pe.edu.upc.easy_book.data.repository

import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookService,
    private val dao: BookDao
) {

    override suspend fun getCatalog(): List<Book> {
        val response = api.getBooks()
        return response.body()?.map { dto ->
            Book(dto.id, dto.title, dto.author, dto.publishedYear, dto.editorial, dto.genre, dto.synopsis, dto.rating, dto.image ?: "")
        } ?: emptyList()
    }

    override fun getLibrary(): Flow<List<Book>> {
        return dao.getReadBooks().map { entities ->
            entities.map { e ->
                Book(e.id, e.title, e.author, e.publishedYear, e.editorial, e.genre, e.synopsis, e.rating, e.image, true, e.readDate)
            }
        }
    }

    override suspend fun getBookById(id: Int): Book {
        val local = dao.getBookById(id)
        if (local != null) return Book(local.id, local.title, local.author, local.publishedYear, local.editorial, local.genre, local.synopsis, local.rating, local.image, true, local.readDate)

        val remote = api.getBookById(id).body() ?: throw Exception("Book not found")
        return Book(remote.id, remote.title, remote.author, remote.publishedYear, remote.editorial, remote.genre, remote.synopsis, remote.rating, remote.image ?: "")
    }

    override suspend fun toggleRead(book: Book) {
        if (book.isRead) {
            dao.delete(book.id)
        } else {
            // Genera la fecha automáticamente [cite: 420]
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            val entity = BookEntity(book.id, book.title, book.author, book.publishedYear, book.editorial, book.genre, book.synopsis, book.rating, book.image, date)
            dao.insert(entity) // Almacena localmente en Room [cite: 426]
        }
    }

    override suspend fun removeBook(id: Int) = dao.delete(id)
}