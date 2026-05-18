package pe.edu.upc.easy_book.data.remote

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("publishedYear")
    val publishedYear: Int,

    @SerializedName("editorial")
    val editorial: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("synopsis")
    val synopsis: String,

    @SerializedName("rating")
    val rating: Double,

    @SerializedName("coverUrl", alternate = ["image", "imageUrl", "cover"])
    val image: String?
)