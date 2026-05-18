package pe.edu.upc.easy_book.domain

import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getCatalog(): List<Book>
    fun getLibrary(): Flow<List<Book>>
    suspend fun getBookById(id: Int): Book
    suspend fun toggleRead(book: Book)
    suspend fun removeBook(id: Int)
}