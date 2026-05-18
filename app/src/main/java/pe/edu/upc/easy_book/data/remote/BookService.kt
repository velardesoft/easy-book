package pe.edu.upc.easy_book.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("books")
    suspend fun getBooks(): Response<List<BookDto>>

    @GET("books/{id}")
    suspend fun getBookById(@Path("id") id: Int): Response<BookDto>
}