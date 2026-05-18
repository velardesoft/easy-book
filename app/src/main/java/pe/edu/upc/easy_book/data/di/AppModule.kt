package pe.edu.upc.easy_book.data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.easy_book.data.local.AppDatabase
import pe.edu.upc.easy_book.data.local.BookDao
import pe.edu.upc.easy_book.data.remote.BookService
import pe.edu.upc.easy_book.data.repository.BookRepositoryImpl
import pe.edu.upc.easy_book.domain.BookRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://bookapp-gveteaa0dqf0eycn.eastus-01.azurewebsites.net/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBookService(retrofit: Retrofit): BookService = retrofit.create(BookService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "bookapp.db"
    ).build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): BookDao = db.bookDao()

    @Provides
    @Singleton
    fun provideRepository(api: BookService, dao: BookDao): BookRepository {
        return BookRepositoryImpl(api, dao)
    }
}