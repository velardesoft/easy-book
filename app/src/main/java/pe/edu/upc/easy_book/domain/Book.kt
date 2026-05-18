package pe.edu.upc.easy_book.domain

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val publishedYear: Int,
    val editorial: String,
    val genre: String,
    val synopsis: String,
    val rating: Double,
    val image: String,
    val isRead: Boolean = false,
    val readDate: String = ""
)
