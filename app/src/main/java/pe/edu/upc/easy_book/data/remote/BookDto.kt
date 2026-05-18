package pe.edu.upc.easy_book.data.remote

import com.google.gson.annotations.SerializedName

data class BookDto(
    val id: Int, val title: String, val author: String,
    val publishedYear: Int, val editorial: String, val genre: String,
    val synopsis: String, val rating: Double,
    @SerializedName("coverUrl", alternate = ["image", "imageUrl"]) val image: String?
)
